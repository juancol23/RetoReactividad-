package com.valdemar.demo.service;

import com.valdemar.demo.model.Prostituta;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProstitutaService {

    Mono<Prostituta> save(Prostituta prostituta);

    Flux<Prostituta> saveAll(Flux<Prostituta>prostituta);

    Flux <Prostituta> findAll();

    Mono<Prostituta> findById(String request);

    Mono<Prostituta> update(Prostituta prostituta, String id);

    public Mono<Void> delete(String id);

    public Mono<Void> deleteList();

}
