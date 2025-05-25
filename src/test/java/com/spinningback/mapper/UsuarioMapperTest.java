package com.spinningback.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.models.Usuario;

public class UsuarioMapperTest {

	@Test
	public void deveMapearUsuarioDTOparaUsuarioComSenhaCriptografada() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto("Teste");
		dto.setEmail("teste@email.com");
		dto.setSenha("senha123");
		dto.setConfirmacaoDeSenha("senha123");
		dto.setTelefone("999999999");
		dto.setCpf("123.456.789-00");

		Usuario usuario = UsuarioMapper.toEntity(dto);

		assertEquals(dto.getNomeCompleto(), usuario.getNomeCompleto());
		assertEquals(dto.getEmail(), usuario.getEmail());
		assertTrue(BCrypt.checkpw("senha123", usuario.getSenha()));
	}

}
