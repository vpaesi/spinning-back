package com.spinningback.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class UsuarioDTO {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @Email
	@Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$", message = "Email deve estar no formato correto")
	@Size(min = 5, max = 50, message = "O email deve ter entre 5 e 50 caracteres")
    private String email;

    @NotBlank
	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotBlank
	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String confirmacaoDeSenha;

    @NotBlank
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXXX-XXXX")
	@Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")	
    private String telefone;

    @NotBlank
	@CPF
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
	@Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    private LocalDate dataDeNascimento;

}
