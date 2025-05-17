package com.spinningback.mapper;

import org.mindrot.jbcrypt.BCrypt;

import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.models.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setCpf(dto.getCpf());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setDataDeNascimento(dto.getDataDeNascimento());

        String senhaCriptografada = BCrypt.hashpw(dto.getSenha(), BCrypt.gensalt());
        usuario.setSenha(senhaCriptografada);

        return usuario;
    }
}
