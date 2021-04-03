package com.valdemar.demo.modelo.dao;

import com.valdemar.demo.modelo.documents.Prostituta;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProstitutaDao extends ReactiveMongoRepository<Prostituta,String> {

    public Mono<Prostituta> findByPseudoNombre(String pseudoNombre);

    @Query("{ 'pseudoNombre': ?0 }")
    public Mono<Prostituta> obtenerPseudoNombre(String pseudoNombre);

    Flux<Prostituta> findByTarifa(Double tarifa);

}
