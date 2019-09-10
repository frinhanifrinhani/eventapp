package br.com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.eventoapp.models.Evento;
import br.com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	EventoRepository eventoRepository;
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
	public String getForm() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST)
	public String saveForm(Evento evento) {
		
		eventoRepository.save(evento);
		
		return "evento/formEvento";
	}

}
