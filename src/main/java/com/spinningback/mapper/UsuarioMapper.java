package com.spinningback.mapper;

import org.mindrot.jbcrypt.BCrypt;

import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.models.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDataDeNascimento(dto.getDataDeNascimento());
		usuario.setCEP(dto.getCEP());
		usuario.setEstado(dto.getEstado());
		usuario.setCidade(dto.getCidade());
		usuario.setBairro(dto.getBairro());
		usuario.setLogradouro(dto.getLogradouro());
		usuario.setNumero(dto.getNumero());
		usuario.setComplemento(dto.getComplemento());

        String senhaCriptografada = BCrypt.hashpw(dto.getSenha(), BCrypt.gensalt());
        usuario.setSenha(senhaCriptografada);

        return usuario;
    }
}
