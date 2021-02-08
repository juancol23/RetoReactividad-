package com.valdemar.demo.handler;

import com.valdemar.demo.modelo.documents.Prostituta;
import com.valdemar.demo.modelo.services.ProstitutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProstitutaHandler {

    @Autowired
    private ProstitutaService prostitutaService;

    public Mono<ServerResponse> listar(ServerRequest request){

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(prostitutaService.findAll(), Prostituta.class);
    }

    public Mono<ServerResponse> crear(ServerRequest request){
        Mono<Prostituta> prostituta = request.bodyToMono(Prostituta.class);

        return prostituta.flatMap(p -> {
                    return prostitutaService.save(p);
                }).flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(BodyInserters.fromObject(p)));

    }


}
