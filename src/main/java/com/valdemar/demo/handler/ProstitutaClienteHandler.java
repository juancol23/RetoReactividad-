package com.valdemar.demo.handler;

import com.valdemar.demo.model.Prostituta;
import com.valdemar.demo.service.ProstitutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProstitutaClienteHandler {

    @Autowired
    private ProstitutaService prostitutaService;

    public Mono<ServerResponse> listar(ServerRequest request){
        return  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(prostitutaService.findAll(), Prostituta.class);
    }
}
