package com.valdemar.demo.handler;

import com.valdemar.demo.modelo.entidad.Prostituta;
import com.valdemar.demo.modelo.services.ProstitutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Component
public class ProstitutaHandler {
    private static final Logger log = LoggerFactory.getLogger(ProstitutaHandler.class);



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

    public Mono<ServerResponse> crearList(ServerRequest request) {
        Flux<Prostituta> prostitutaFlux = request.bodyToFlux(Prostituta.class);

        return prostitutaFlux.collectList()
                .map(data->data)
                .flatMap(p -> {
                    if(p.size() > 0){
                        p.parallelStream().forEach(response -> {
                            prostitutaService.save(response).subscribe();
                            log.info("PseuNombre: "+response.getPseudoNombre());
                            log.info("Tarifa: "+response.getTarifa());
                            log.info("Estado: "+response.getEstado());
                        });
                    }

                    return Mono.just(p);

                })
                .flatMap(flat -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("Se registró correctamente la lista")));
    }





}
