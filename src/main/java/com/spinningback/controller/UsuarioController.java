package com.spinningback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spinningback.models.Usuario;
import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.services.UsuarioService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioService.criarUsuario(usuarioDTO);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUmUsuario(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.buscarUmUsuario(id);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Long id,
			@Valid @RequestBody Usuario usuarioAtualizado) {
		Usuario atualizado = usuarioService.atualizarUsuario(id, usuarioAtualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) {
		usuarioService.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
