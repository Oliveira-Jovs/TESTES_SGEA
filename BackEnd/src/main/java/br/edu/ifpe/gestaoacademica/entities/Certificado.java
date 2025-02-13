package br.edu.ifpe.gestaoacademica.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Certificado")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Certificado {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cargaHoraria;
    private String descricao;
    private boolean ativo;
    
    @OneToOne
	@JoinColumn(name = "id_evento")
	@JsonIgnore
	private Evento evento;
    
    @OneToMany(mappedBy = "certificado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participante> participantes;

    public void inativar() {
        this.ativo = false;
    }
}
