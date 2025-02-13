package br.edu.ifpe.gestaoacademica.controllers.dto;

import br.edu.ifpe.gestaoacademica.entities.Banco;
import br.edu.ifpe.gestaoacademica.entities.Endereco;
import br.edu.ifpe.gestaoacademica.entities.Servidor;
import br.edu.ifpe.gestaoacademica.entities.Utilizador;
import jakarta.validation.constraints.NotBlank;

public record ServidorDTO(Long id, String siape, @NotBlank String cargo, @NotBlank String nome, @NotBlank String cpf,
		@NotBlank String rg, @NotBlank String dataNasc, @NotBlank String telefone, @NotBlank String email,
		Endereco endereco, Banco banco, Utilizador utilizador) {

	// Construtor para cadastrar servidor
	public ServidorDTO(String siape, String cargo, String nome, String cpf, String rg, String dataNasc, String telefone,
			String email, Endereco endereco, Banco banco, Utilizador utilizador) {
		this(null, siape, cargo, nome, cpf, rg, dataNasc, telefone, email, endereco, banco, utilizador);
	}

	// Construtor para listar servidor
	public ServidorDTO(Servidor servidor) {
		this(servidor.getId(), servidor.getSiape(), servidor.getCargo(), servidor.getNome(), servidor.getCpf(),
				servidor.getRg(), servidor.getDataNasc(), servidor.getTelefone(), servidor.getEmail(),
				servidor.getEndereco(), servidor.getBanco(), servidor.getUtilizador());
	}

	// Construtor para atualizar servidor
	public ServidorDTO(Long id, String siape, String cargo, String nome, String cpf, String rg, String dataNasc,
			String telefone, String email, Endereco endereco, Banco banco,  Utilizador utilizador) {
		this.id = id;
		this.siape = siape;
		this.cargo = cargo;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.banco = banco;
		this.utilizador = utilizador;

	}
}
