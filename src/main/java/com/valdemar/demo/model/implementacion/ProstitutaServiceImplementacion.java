package com.valdemar.demo.model.implementacion;

import com.valdemar.demo.model.Prostituta;
import com.valdemar.demo.service.ProstitutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class ProstitutaServiceImplementacion implements ProstitutaService {

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Prostituta> save(Prostituta prostituta) {
        return webClient.post()
                .uri("/api/v1/prostitutas/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(prostituta)
                .retrieve()
                .bodyToMono(Prostituta.class);
    }

    @Override
    public Flux<Prostituta> saveAll(Flux<Prostituta> prostituta) {
        return null;
    }

    @Override
    public Flux<Prostituta> findAll() {
        return webClient
                .get()
                .retrieve()
                .bodyToFlux(Prostituta.class);
    }

    @Override
    public Mono<Prostituta> findById(String request) {
        //String id = request.pathVariable("id");
        String id = request;
        return webClient.get().uri("/api/v1/prostitutas/" + id).retrieve().bodyToMono(Prostituta.class);

    }

    @Override
    public Mono<Prostituta> update(Prostituta prostituta, String id) {
        return webClient.put()
                .uri("/api/v1/prostitutas/{id}",
                        Collections.singletonMap("id",id))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(prostituta)
                .retrieve()
                .bodyToMono(Prostituta.class);
    }


    @Override
    public Mono<Void> delete(String id) {
        /*return webClient.delete()
                .uri("/api/v1/prostitutas/{id}", Collections.singletonMap("id",id))
                .retrieve();*/
        return null;
    }

    @Override
    public Mono<Void> deleteList() {
        return null;
    }






}
