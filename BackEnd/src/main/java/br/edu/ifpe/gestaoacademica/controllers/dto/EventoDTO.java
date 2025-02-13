package br.edu.ifpe.gestaoacademica.controllers.dto;

import br.edu.ifpe.gestaoacademica.entities.Evento;
import br.edu.ifpe.gestaoacademica.entities.Servidor;
import jakarta.validation.constraints.NotBlank;

public record EventoDTO(
		Long id,
		@NotBlank
		String nome,
		@NotBlank
		String descricao,
		@NotBlank
		String data,
		@NotBlank
		String local,
		@NotBlank
		String tipo,
		Servidor servidor) {

	//Construtor para cadastrar evento
	public EventoDTO(String nome, String descricao, String data, String local, String tipo, Servidor servidor) {
		this(null, nome, descricao, data, local, tipo, servidor);
	}

	//Construtor para listar evento
	public EventoDTO(Evento evento) {
		this(evento.getId(), evento.getNome(), evento.getDescricao(), evento.getData(), evento.getLocal(), evento.getTipo(), 
			 evento.getServidor());
	}
	
	//Construtor para atualizar evento
	public EventoDTO(Long id, String nome, String descricao, String data, String local, String tipo, Servidor servidor) {
		this.id = id; 
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.local = local;
		this.tipo = tipo;
		this.servidor = servidor;
	}
}
