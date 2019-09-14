package br.com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.eventoapp.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

}
