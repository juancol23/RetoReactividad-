package com.valdemar.demo;

import com.valdemar.demo.modelo.documents.Prostituta;
import com.valdemar.demo.modelo.services.ProstitutaService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetoServidorApplicationTests {

	@Autowired
	private WebTestClient client;

	@Autowired
	private ProstitutaService prostitutaService;

	@Test
	public void listar() {
		client
				.get()
				.uri("/api/v1/prostitutas")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.valueOf("application/problem+json;charset=UTF-8"))
				.expectBodyList(Prostituta.class)
				.consumeWith(response->{
					List<Prostituta> prostitutas = response.getResponseBody();
					prostitutas.forEach(p->{
						System.out.println("PseudoNombre: "+p.getPseudoNombre());

					});

				});
	}

	@Test
	public void detalle() {
		//Mono<Prostituta> prostitutaMono = prostitutaService.findByPseudoNombre("PUta");
		Prostituta prostitutaMono = prostitutaService.findByPseudoNombre("Conchatumadre").block();
		client
				.get()
				.uri("/api/v1/prostitutas/{id}", Collections.singletonMap("id",prostitutaMono.getId()))
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.valueOf("application/problem+json;charset=UTF-8"))
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.pseudoNombre").isNotEmpty()
				;
	}

	@Test
	public void crearProstituta() {
		//Mono<Prostituta> prostitutaMono = prostitutaService.findByPseudoNombre("PUta");
		Prostituta prostitutaMono = new Prostituta("Adriana",true,3000.00);
		client
				.post()
				.uri("/api/v1/prostitutas/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(prostitutaMono),Prostituta.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.valueOf("application/problem+json;charset=UTF-8"))
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.pseudoNombre").isEqualTo("Adriana");
		;
	}


	@Test
	public void actualizarProstituta() {
		//Mono<Prostituta> prostitutaMono = prostitutaService.findByPseudoNombre("PUta");
		Prostituta prostitutaMono = prostitutaService.findByPseudoNombre("GOLFA").block();
		Prostituta prostitutaEditada = new Prostituta("GOLFA",true,500.00);

		client
				.put()
				.uri("/api/v1/prostitutas/{id}",Collections.singletonMap("id",prostitutaMono.getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(prostitutaEditada),Prostituta.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.valueOf("application/problem+json;charset=UTF-8"))
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.pseudoNombre").isEqualTo("GOLFA");
		;
	}

	@Test
	public void eliminarProstituta() {
		//Mono<Prostituta> prostitutaMono = prostitutaService.findByPseudoNombre("PUta");
		Prostituta prostitutaMono = prostitutaService.findByPseudoNombre("Conchatumadre").block();

		client
				.delete()
				.uri("/api/v1/prostitutas/{id}",Collections.singletonMap("id",prostitutaMono.getId()))
				.exchange()
				.expectStatus().isNoContent()
				.expectBody()
				.isEmpty();
	}

}
