package com.valdemar.demo.modelo.services;

import com.valdemar.demo.modelo.documents.Prostituta;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public interface ProstitutaService {
    Mono<Prostituta> save(Prostituta prostituta);
    Flux<Prostituta> saveAll(Flux<Prostituta>prostituta);

    Flux <Prostituta> findAll();
    Mono<Prostituta> findById(String request);

    public Mono<Void> delete(Prostituta prostituta);

    public Mono<Void> deleteList();

    public Mono<Prostituta> findByPseudoNombre(String pseudoNombre);


}
