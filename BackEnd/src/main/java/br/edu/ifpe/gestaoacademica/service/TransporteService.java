package br.edu.ifpe.gestaoacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.edu.ifpe.gestaoacademica.controllers.dto.TransporteDTO;
import br.edu.ifpe.gestaoacademica.entities.Evento;
import br.edu.ifpe.gestaoacademica.entities.Transporte;
import br.edu.ifpe.gestaoacademica.repository.EventoRepository;
import br.edu.ifpe.gestaoacademica.repository.TransporteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")

@Service
public class TransporteService {
	
	@Autowired
	private TransporteRepository transporteRepository;
	
	@Autowired
    private EventoRepository eventoRepository;
	

	public Transporte cadastrarTransporte(TransporteDTO dadosTransporteDTO) {

		Transporte transporte = new Transporte();
		transporte.setCategoria(dadosTransporteDTO.categoria());
		transporte.setPlaca(dadosTransporteDTO.placa());
		transporte.setQuilometragem(dadosTransporteDTO.quilometragem());
		transporte.setNomeMotorista(dadosTransporteDTO.nomeMotorista());
		transporte.setHoraSaida(dadosTransporteDTO.horaSaida());
		transporte.setHoraChegada(dadosTransporteDTO.horaChegada());
		transporte.setAtivo(true);
		
		Evento evento = eventoRepository.findById(dadosTransporteDTO.idEvento())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
        transporte.setEvento(evento);
		transporte.setServidores(dadosTransporteDTO.servidor());
		
		return transporteRepository.save(transporte);
	}

	public List<Transporte> listarTransporte() {
		return transporteRepository.findAllByAtivoTrue();
	}
	
	// Método para listar as transportes de um evento específico
    public List<Transporte> listarTransportesPorEvento(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
        return transporteRepository.findByEvento(evento);
    }
	
	public Transporte atualizarTransporte(@Valid TransporteDTO dadosTransporteDTO) {
		
	    Transporte transporte = transporteRepository.findById(dadosTransporteDTO.id())
	            .orElseThrow(() -> new EntityNotFoundException("Transporte não encontrado"));

		if (dadosTransporteDTO.categoria() != null) 	transporte.setCategoria(dadosTransporteDTO.categoria());
		if (dadosTransporteDTO.placa() != null) 		transporte.setPlaca(dadosTransporteDTO.placa());
		if (dadosTransporteDTO.quilometragem() != null) transporte.setQuilometragem(dadosTransporteDTO.quilometragem());
		if (dadosTransporteDTO.nomeMotorista() != null) transporte.setNomeMotorista(dadosTransporteDTO.nomeMotorista());
		if (dadosTransporteDTO.horaSaida() != null) 	transporte.setHoraSaida(dadosTransporteDTO.horaSaida());
		if (dadosTransporteDTO.horaChegada() != null) 	transporte.setHoraChegada(dadosTransporteDTO.horaChegada());
		
		return transporteRepository.save(transporte);
	}
	
	public void inativarTransporte(Long id) {
		Transporte transporte = transporteRepository.getReferenceById(id);
		transporte.inativar();
		transporteRepository.save(transporte);
	}

	public void deletarTransporte(Long id) {
		transporteRepository.deleteById(id);
	}
}
