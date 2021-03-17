package com.valdemar.demo;

import com.valdemar.demo.modelo.services.ProstitutaService;
import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.DatabasePopulatorUtils;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
public class Reto01Application implements CommandLineRunner{
	@Autowired
	private ProstitutaService prostitutaService;

	@Autowired
	ConnectionFactory connectionFactory;

	//@Autowired
	//private ReactiveMongoTemplate mongoTemplate;
	private static final Logger log = LoggerFactory.getLogger(Reto01Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Reto01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
		DatabasePopulatorUtils.execute(databasePopulator, connectionFactory).block();

	}

	@Bean
	public WebFluxConfigurer corsConfigurer() {
		return new WebFluxConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}


}
