package com.spinningback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.models.Usuario;
import com.spinningback.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioService usuarioService;

	@Autowired
	private ObjectMapper objectMapper;

	private Usuario usuario;
	private UsuarioDTO usuarioDTO;

	@BeforeEach
	public void setUp() {
		usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNomeCompleto("Maria Camargo");
		usuarioDTO.setEmail("maria@example.com");
		usuarioDTO.setSenha("123456");
		usuarioDTO.setConfirmacaoDeSenha("123456");
		usuarioDTO.setDataDeNascimento(LocalDate.of(1990, 5, 17));
		usuarioDTO.setTelefone("(11) 91234-5678");
		usuarioDTO.setCpf("123.456.789-09");

		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNomeCompleto("Maria Camargo");
		usuario.setEmail("maria@example.com");
		usuario.setSenha("123456");
		usuario.setDataDeNascimento(LocalDate.of(1990, 5, 17));
		usuario.setTelefone("(11) 91234-5678");
		usuario.setCpf("123.456.789-09");
	}

	@Test
	@WithMockUser
	public void deveCriarUsuario() throws Exception {
		Mockito.when(usuarioService.criarUsuario(any(UsuarioDTO.class))).thenReturn(usuario);

		mockMvc.perform(post("/usuario")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(usuarioDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nomeCompleto").value("Maria Camargo"))
				.andExpect(jsonPath("$.email").value("maria@example.com"));
	}

	@Test
	@WithMockUser
	public void deveBuscarUsuarioPorId() throws Exception {
		Mockito.when(usuarioService.buscarUmUsuario(1L)).thenReturn(usuario);

		mockMvc.perform(get("/usuario/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.email").value("maria@example.com"));
	}

	@Test
	@WithMockUser
	public void deveBuscarTodosUsuarios() throws Exception {
		List<Usuario> usuarios = Arrays.asList(usuario);
		Mockito.when(usuarioService.buscarTodosUsuarios()).thenReturn(usuarios);

		mockMvc.perform(get("/usuario/todos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(1))
				.andExpect(jsonPath("$[0].email").value("maria@example.com"));
	}

	@Test
	@WithMockUser
	public void deveAtualizarUsuario() throws Exception {
		Usuario atualizado = new Usuario();
		atualizado.setId(1L);
		atualizado.setNomeCompleto("Maria Atualizada");
		atualizado.setEmail("nova@example.com");
		atualizado.setSenha("654321");
		atualizado.setDataDeNascimento(LocalDate.of(1990, 5, 17));
		atualizado.setTelefone("(11) 91234-5678");
		atualizado.setCpf("123.456.789-09");

		Mockito.when(usuarioService.atualizarUsuario(eq(1L), any(Usuario.class))).thenReturn(atualizado);

		mockMvc.perform(put("/usuario/1")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(atualizado)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nomeCompleto").value("Maria Atualizada"))
				.andExpect(jsonPath("$.email").value("nova@example.com"));
	}

	@Test
	@WithMockUser
	public void deveDeletarUsuario() throws Exception {
		Mockito.doNothing().when(usuarioService).deletarUsuario(1L);

		mockMvc.perform(delete("/usuario/1")
				.with(csrf()))
				.andExpect(status().isNoContent());
	}
}
