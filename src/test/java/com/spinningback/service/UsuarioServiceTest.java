package com.spinningback.service;

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

	@Test
	public void deveCriarUsuario() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto("Fulano");
		dto.setEmail("fulano@email.com");
		dto.setSenha("123456");
		dto.setConfirmacaoDeSenha("123456");
		dto.setTelefone("999999999");
		dto.setCpf("12345678901");

		UsuarioService service = new UsuarioService();
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
		UsuarioService usuarioService = new UsuarioService();
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto("Maria Camargo");
		dto.setEmail("maria@example.com");
		dto.setSenha("123456");
		dto.setConfirmacaoDeSenha("123456");
		dto.setDataDeNascimento(LocalDate.of(1990, 5, 17));

		Usuario criado = usuarioService.criarUsuario(dto); // cria no HashMap
		Long idCriado = criado.getId();

		// Agora sim podemos deletar
		usuarioService.deletarUsuario(idCriado);

		assertThrows(NoSuchElementException.class, () -> usuarioService.buscarUmUsuario(idCriado));
	}

	@Test
	public void deveAtualizarUsuarioPorId() {
		UsuarioService service = new UsuarioService();
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto("Joana");
		dto.setEmail("joana@email.com");
		dto.setSenha("senha123");
		dto.setConfirmacaoDeSenha("senha123");
		dto.setTelefone("99999999");
		dto.setCpf("12345678900");

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
		UsuarioService service = new UsuarioService();
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto("Carlos");
		dto.setEmail("carlos@email.com");
		dto.setSenha("123456");
		dto.setConfirmacaoDeSenha("123456");
		dto.setTelefone("99999999");
		dto.setCpf("12345678900");

		Usuario criado = service.criarUsuario(dto);
		Usuario encontrado = service.buscarUmUsuario(criado.getId());

		assertNotNull(encontrado);
		assertTrue(encontrado.getEmail().equals("carlos@email.com"));
	}

}
