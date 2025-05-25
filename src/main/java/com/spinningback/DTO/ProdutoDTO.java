package com.spinningback.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")   
	private String nome;

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Descrição deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "A Descrição deve ter entre 2 e 50 caracteres")  
	private String descricao;

	@NotBlank
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Preço deve ser um número com até duas casas decimais")
	@Size(min = 2, max = 10, message = "O preço deve ter entre 2 e 10 caracteres")
	private Double preco;

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Categoria deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "A Categoria deve ter entre 2 e 50 caracteres")
	private String categoria;

	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Imagem deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "A Imagem deve ter entre 2 e 50 caracteres")	
	private String imagem;

	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Informação extra deve conter apenas letras e espaços")
	@Size(min = 2, max = 50, message = "A Informação extra deve ter entre 2 e 50 caracteres")
	private String informacaoExtra;
	
}
