package com.valdemar.demo;

import com.valdemar.demo.modelo.services.ProstitutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class RetoServidorApplication implements CommandLineRunner{
	@Autowired
	private ProstitutaService prostitutaService;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	private static final Logger log = LoggerFactory.getLogger(RetoServidorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RetoServidorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//mongoTemplate.dropCollection("prostituta").subscribe();
/*
 		Flux.just(new Prostituta("Vanessa",true,130.00),
				new Prostituta("Miluska",true,230.00),
				new Prostituta("Sofia",true,500.00),
				new Prostituta("Katherine",true,90.00))
		.flatMap(prostitutaService::save)
		.subscribe(prostituta -> log.info("insert: "+prostituta.getPseudoNombre()));
		*/

	}

}