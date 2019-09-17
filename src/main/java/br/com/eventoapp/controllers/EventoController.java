package br.com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventoapp.models.Convidado;
import br.com.eventoapp.models.Evento;
import br.com.eventoapp.repository.ConvidadoRepository;
import br.com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	EventoRepository eventoRepository;
	
	@Autowired
	ConvidadoRepository convidadoRepository;
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
	public String getForm() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST)
	public String saveForm(Evento evento) {
		
		eventoRepository.save(evento);
		
		return "evento/formEvento";
	}
	
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos(){
		ModelAndView modelAndView = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		modelAndView.addObject("eventos",eventos);
		
		return modelAndView;
		
	}
	
	@RequestMapping(value =  "detalheEvento/{codigo}" , method = RequestMethod.GET)
	public ModelAndView detalheEvento(@PathVariable("codigo") long 	codigo ) {
		Evento evento = getCodigo(codigo);
		ModelAndView modelAndView = new ModelAndView("evento/detalheEvento");
		modelAndView.addObject("evento", evento);
		
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		modelAndView.addObject("convidados", convidados);
		
		return modelAndView; 
		
	}
	
	@RequestMapping(value = "detalheEvento/{codigo}", method = RequestMethod.POST)
	public String detalheEventoPost(@PathVariable("codigo") long codigo, Convidado convidado){
		Evento evento = getCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		return "redirect:/detalheEvento/{codigo}";
	}

	private Evento getCodigo(long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		return evento;
	}

}
