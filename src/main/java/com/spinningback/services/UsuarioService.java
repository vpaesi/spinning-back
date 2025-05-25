package com.spinningback.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.mapper.UsuarioMapper;
import com.spinningback.models.Usuario;

@Service
public class UsuarioService {

	private final Map<Long, Usuario> usuarios = new HashMap<>();
	private long nextId = 1;

	// CREATE
	public Usuario criarUsuario(UsuarioDTO dto) {
		if (!dto.getSenha().equals(dto.getConfirmacaoDeSenha())) {
			throw new IllegalArgumentException("As senhas não coincidem.");
		}

		Usuario usuario = UsuarioMapper.toEntity(dto);
		usuario.setId(nextId++);
		usuarios.put(usuario.getId(), usuario);
		return usuario;
	}

	// READ
	public Usuario buscarUmUsuario(Long id) {
		Usuario usuario = usuarios.get(id);
		if (usuario == null) {
			throw new NoSuchElementException("Usuário com ID " + id + " não encontrado.");
		}
		return usuario;
	}

	public List<Usuario> buscarTodosUsuarios() {
		return new ArrayList<>(usuarios.values());
	}

	// UPDATE
	public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
		Usuario existente = usuarios.get(id);
		if (existente == null) {
			throw new NoSuchElementException("Usuário não encontrado");
		}
		atualizarCamposUsuario(existente, usuarioAtualizado);
		usuarios.put(id, existente);
		return existente;
	}

	private void atualizarCamposUsuario(Usuario existente, Usuario atualizado) {
		existente.setNomeCompleto(atualizado.getNomeCompleto());
		existente.setCpf(atualizado.getCpf());
		existente.setEmail(atualizado.getEmail());
		existente.setTelefone(atualizado.getTelefone());
		String senhaCriptografada = BCrypt.hashpw(atualizado.getSenha(), BCrypt.gensalt());
		existente.setSenha(senhaCriptografada);
		existente.setDataDeNascimento(atualizado.getDataDeNascimento());
		existente.setCEP(atualizado.getCEP());
		existente.setEstado(atualizado.getEstado());
		existente.setCidade(atualizado.getCidade());
		existente.setBairro(atualizado.getBairro());
		existente.setLogradouro(atualizado.getLogradouro());
		existente.setNumero(atualizado.getNumero());
		existente.setComplemento(atualizado.getComplemento());
	}

	// DELETE
	public void deletarUsuario(Long id) {
		usuarios.remove(id);
	}

}
