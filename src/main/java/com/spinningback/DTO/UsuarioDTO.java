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
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    private String nomeCompleto;

    @NotBlank
	@CPF
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
	@Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    @NotBlank
    @Email
	@Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$", message = "Email deve estar no formato correto")
	@Size(min = 5, max = 50, message = "O email deve ter entre 5 e 50 caracteres")
    private String email;
	
	@NotBlank
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXXX-XXXX")
	@Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")	
	private String telefone;

    @NotBlank
	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotBlank
	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String confirmacaoDeSenha;

    private LocalDate dataDeNascimento;

	@NotBlank
	@Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
	@Size(min = 8, max = 10, message = "O CEP deve ter entre 8 e 10 caracteres")
	private String CEP;

	@NotBlank
	@Pattern(regexp = "^[A-Z]{2}$", message = "Estado deve conter apenas duas letras maiúsculas")
	@Size(min = 2, max = 2, message = "O estado deve ter exatamente 2 caracteres")
	private String estado;

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Cidade deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "A cidade deve ter entre 2 e 50 caracteres")
	private String cidade;

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Bairro deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "O bairro deve ter entre 2 e 50 caracteres")
	private String bairro;

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Rua deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "A rua deve ter entre 2 e 50 caracteres")
	private String logradouro;

	@NotBlank
	@Pattern(regexp = "^\\d+$", message = "Número deve conter apenas dígitos")
	@Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres")
	private String numero;

	@Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s]+$", message = "Complemento deve conter apenas letras, números e espaços")
	@Size(max = 50, message = "O complemento deve ter no máximo 50 caracteres")
	private String complemento;

}
