package com.valdemar.demo.handler;

import com.mongodb.internal.connection.Server;
import com.valdemar.demo.modelo.documents.Prostituta;
import com.valdemar.demo.modelo.documents.ProstitutaRequest;
import com.valdemar.demo.modelo.services.ProstitutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class ProstitutaHandler {
    private static final Logger log = LoggerFactory.getLogger(ProstitutaHandler.class);

    WebClient webClient = WebClient.create("http://localhost:8080");
 

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



    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(prostitutaService.findById(id), Prostituta.class);
    }

    public Mono<ServerResponse> update(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Prostituta> prostitutaRequest = request.bodyToMono(Prostituta.class);
        Mono<Prostituta> prostitutaDb = prostitutaService.findById(id);

        return prostitutaDb.zipWith(prostitutaRequest, (prostitutaDb_,prostitutaRequest_) ->{
            prostitutaDb_.setPseudoNombre(prostitutaRequest_.getPseudoNombre());
            prostitutaDb_.setEstado(prostitutaRequest_.getEstado());
            prostitutaDb_.setTarifa(prostitutaRequest_.getTarifa());

            return prostitutaDb_;
        }).flatMap(p-> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(prostitutaService.save(p), Prostituta.class))
                .switchIfEmpty(ServerResponse.notFound().build());

    }
    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Prostituta> prostitutaDb = prostitutaService.findById(id);

        return prostitutaDb.flatMap(p->prostitutaService.delete(p).then(ServerResponse.noContent().build()));

    };

    public Mono<ServerResponse> deleteList(ServerRequest request){
        prostitutaService.deleteList().subscribe();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(BodyInserters.fromValue("Se eliminó toda la lista"));

    };

    public Mono<ServerResponse> leer(ServerRequest request){
       /*
        Flux<Prostituta> findAllProstitutas = findAllProstitutas();
        findAllProstitutas.collectList()
                .map(data->data)
                .flatMap(p->{

                    if(p.size() > 0){
                    p.parallelStream().forEach(response -> {
                    log.info("PseuNombre: "+response.getPseudoNombre());
                    log.info("Tarifa: "+response.getTarifa());
                    log.info("Estado: "+response.getEstado());
                    });


                 }
                    return Mono.just(p);

                }).subscribe();


        String id = request.pathVariable("id");

        Mono<Prostituta> puta = findPuta(id);
                Mono<Prostituta> prostitutaMono = request.bodyToMono(Prostituta.class);

     */

        String id = request.pathVariable("id");

        Flux<Prostituta> prostitutaFlux = request.bodyToFlux(Prostituta.class);



        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(prostitutaFlux.flatMap(p->
                    updatePuta(p,id)
                    )
                    ,Prostituta.class);
        };

    public Flux<Prostituta> findAllProstitutas(){
        return webClient
                .get()
                .uri("/api/v1/prostitutas")
                .retrieve()
                .bodyToFlux(Prostituta.class);
    }


    public Mono<Prostituta> findPuta(String id){
        return webClient.get().uri("/api/v1/prostitutas/" + id).retrieve().bodyToMono(Prostituta.class);
    }

    public Flux<Prostituta> crearPuta(Flux <Prostituta> prostituta){
        return webClient.post()
                .uri("/api/v1/prostitutas/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(prostituta)
                .retrieve()
                .bodyToFlux(Prostituta.class);
    }
    public Mono<Prostituta> updatePuta(Prostituta prostituta, String id){
        return webClient.put()
                .uri("/api/v1/prostitutas/{id}", Collections.singletonMap("id",id))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(prostituta)
                .retrieve()
                .bodyToMono(Prostituta.class);
    }
}
