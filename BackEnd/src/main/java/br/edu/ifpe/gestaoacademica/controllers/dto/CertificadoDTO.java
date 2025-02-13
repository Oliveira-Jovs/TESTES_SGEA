package br.edu.ifpe.gestaoacademica.controllers.dto;

import br.edu.ifpe.gestaoacademica.entities.Certificado;
import jakarta.validation.constraints.NotBlank;

public record CertificadoDTO(
		Long id,
		@NotBlank
		String cargaHoraria,
		@NotBlank
		String descricao,
		Long idEvento) {
	
	//Construtor para cadastrar certificado
	public CertificadoDTO(String cargaHoraria,String descricao,Long idEvento) {
		this(null, cargaHoraria, descricao, idEvento);
	}
	
	//Construtor para listar certificado
	public CertificadoDTO(Certificado certificado) {
		this(certificado.getId(), certificado.getCargaHoraria(), certificado.getDescricao(), certificado.getEvento() != null ? certificado.getEvento().getId() : null);
	}
	
	//Construtor para atualizar certificado
	public CertificadoDTO(Long id,String cargaHoraria,String descricao,Long idEvento) {
		this.id = id;
		this.cargaHoraria = cargaHoraria;
		this.descricao = descricao;
		this.idEvento = idEvento;
	}
}
