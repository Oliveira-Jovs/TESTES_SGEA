package br.edu.ifpe.gestaoacademica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpe.gestaoacademica.controllers.dto.EventoDTO;
import br.edu.ifpe.gestaoacademica.entities.Evento;
import br.edu.ifpe.gestaoacademica.service.EventoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@PostMapping
	@Transactional
	public ResponseEntity<Evento> cadastrarEvento(@RequestBody @Valid EventoDTO dadosEventoDTO) {
		Evento evento = eventoService.cadastrarEvento(dadosEventoDTO);
		return ResponseEntity.ok(evento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EventoDTO> listarEventoId(@PathVariable Long id) {
	    Optional<Evento> eventoOptional = eventoService.listarEventoId(id);
	    
	    if (eventoOptional.isEmpty()) {
	        return ResponseEntity.notFound().build();  // Retorna 404 caso o servidor não seja encontrado
	    }

	    EventoDTO eventoDTO = new EventoDTO(eventoOptional.get());  // Converte para o DTO
	    return ResponseEntity.ok(eventoDTO);  // Retorna o servidor encontrado com status 200
	}

	@GetMapping
	public List<EventoDTO> listarEvento() {
		return eventoService.listarEventos().stream().map(EventoDTO::new).toList();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Evento> atualizarEvento(@RequestBody @Valid EventoDTO dadosEventoDTO) {
		Evento evento = eventoService.atualizarEvento(dadosEventoDTO);
		return ResponseEntity.ok(evento);
	}

	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
		eventoService.deletarEvento(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> inativarEvento(@PathVariable Long id) {
		eventoService.inativarEvento(id);
		return ResponseEntity.noContent().build();
	}
}
