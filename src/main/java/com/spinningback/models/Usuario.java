package com.spinningback.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nomeCompleto;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String senha;

	@NotBlank
	@Column(nullable = false)
	private String telefone;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(nullable = true)
	private LocalDate dataDeNascimento;
	
}
