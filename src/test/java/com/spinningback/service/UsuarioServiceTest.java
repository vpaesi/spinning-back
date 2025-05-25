package com.spinningback.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.models.Usuario;
import com.spinningback.services.UsuarioService;

public class UsuarioServiceTest {

	private UsuarioDTO criarUsuarioDTO(String nome, String email) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto(nome);
		dto.setEmail(email);
		dto.setSenha("123456");
		dto.setConfirmacaoDeSenha("123456");
		dto.setTelefone("99999999");
		dto.setCpf("12345678900");
		return dto;
	}

	private UsuarioService novoUsuarioService() {
		return new UsuarioService();
	}
	
	@Test
	public void deveCriarUsuario() {
		UsuarioDTO dto = criarUsuarioDTO("Fulano", "fulano@email.com");

		UsuarioService service = novoUsuarioService();
		Usuario usuario = service.criarUsuario(dto);

		assertNotNull(usuario.getId());
		assertNotEquals("123456", usuario.getSenha());
		assertTrue(BCrypt.checkpw("123456", usuario.getSenha()));
	}

	@Test
	public void deveCriptografarSenha() {
		String senha = "minhaSenha123";
		String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

		assertTrue(BCrypt.checkpw("minhaSenha123", hash));
		assertFalse(BCrypt.checkpw("outraSenha", hash));
	}

	@Test
	public void deveDeletarUsuarioPorId() {
		UsuarioService usuarioService = novoUsuarioService();
		UsuarioDTO dto = criarUsuarioDTO("Maria Camargo", "maria@example.com");
		dto.setDataDeNascimento(LocalDate.of(1990, 5, 17));

		Usuario criado = usuarioService.criarUsuario(dto);
		Long idCriado = criado.getId();

		usuarioService.deletarUsuario(idCriado);

		assertThrows(NoSuchElementException.class, () -> usuarioService.buscarUmUsuario(idCriado));
	}

	@Test
	public void deveAtualizarUsuarioPorId() {
		UsuarioService service = novoUsuarioService();
		UsuarioDTO dto = criarUsuarioDTO("Joana", "joana@email.com");
		
		Usuario usuario = service.criarUsuario(dto);
		Long id = usuario.getId();

		Usuario atualizado = new Usuario();
		atualizado.setNomeCompleto("Joana Atualizada");
		atualizado.setEmail("nova@email.com");

		Usuario resultado = service.atualizarUsuario(id, atualizado);

		assertNotNull(resultado);
		assertTrue(resultado.getNomeCompleto().equals("Joana Atualizada"));
		assertTrue(resultado.getEmail().equals("nova@email.com"));
	}

	@Test
	public void deveRetornarUsuarioPorId() {
		UsuarioService service = novoUsuarioService();
		UsuarioDTO dto = criarUsuarioDTO("Carlos", "carlos@email.com");
		
		Usuario criado = service.criarUsuario(dto);
		Usuario encontrado = service.buscarUmUsuario(criado.getId());

		assertNotNull(encontrado);
		assertEquals("carlos@email.com", encontrado.getEmail());
	}

}
