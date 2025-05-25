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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
		usuarioDTO = criarUsuarioDTO();
		usuario = criarUsuario();
	}

	private UsuarioDTO criarUsuarioDTO() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNomeCompleto("Maria Camargo");
		dto.setCpf("111.444.777-35");
		dto.setEmail("maria@example.com");
		dto.setTelefone("(11) 91234-5678");
		dto.setSenha("123456");
		dto.setConfirmacaoDeSenha("123456");
		dto.setDataDeNascimento(LocalDate.of(1990, 5, 17));
		dto.setCEP("12345-678");
		dto.setEstado("SP");
		dto.setCidade("São Paulo");
		dto.setBairro("Jardim das Rosas");
		dto.setLogradouro("Rua das Flores");
		dto.setNumero("123");
		dto.setComplemento("Apto 45");
		return dto;
	}

	private Usuario criarUsuario() {
		Usuario user = new Usuario();
		user.setId(1L);
		user.setNomeCompleto("Maria Camargo");
		user.setCpf("123.456.789-09");
		user.setEmail("maria@example.com");
		user.setTelefone("(11) 91234-5678");
		user.setSenha("123456");
		user.setDataDeNascimento(LocalDate.of(1990, 5, 17));
		user.setCEP("12345-678");
		user.setEstado("SP");
		user.setCidade("São Paulo");
		user.setBairro("Jardim das Rosas");
		user.setLogradouro("Rua das Flores");
		user.setNumero("123");
		user.setComplemento("Apto 45");
		return user;
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
	public void deveDeletarUsuario() throws Exception {
		Mockito.doNothing().when(usuarioService).deletarUsuario(1L);

		mockMvc.perform(delete("/usuario/1")
				.with(csrf()))
				.andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser
	public void deveAtualizarUsuario() throws Exception {
		Usuario usuarioAtualizado = criarUsuarioAtualizado();

		Mockito.when(usuarioService.atualizarUsuario(Mockito.eq(1L), any(Usuario.class)))
				.thenReturn(usuarioAtualizado);

		mockMvc.perform(put("/usuario/1")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(usuarioAtualizado)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.nomeCompleto").value("Maria Atualizada"))
				.andExpect(jsonPath("$.email").value("maria.atualizada@example.com"));
	}

	private Usuario criarUsuarioAtualizado() {
		Usuario user = new Usuario();
		user.setId(1L);
		user.setNomeCompleto("Maria Atualizada");
		user.setCpf("123.456.789-09");
		user.setEmail("maria.atualizada@example.com");
		user.setTelefone("(11) 91234-5678");
		user.setSenha("123456");
		user.setDataDeNascimento(LocalDate.of(1990, 5, 17));
		user.setCEP("12345-678");
		user.setEstado("SP");
		user.setCidade("São Paulo");
		user.setBairro("Jardim das Rosas");
		user.setLogradouro("Rua das Flores");
		user.setNumero("123");
		user.setComplemento("Apto 45");
		return user;
	}
}