package com.valdemar.demo.router;

import com.valdemar.demo.handler.ProstitutaClienteHandler;
import com.valdemar.demo.service.ProstitutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {



    @Bean
    public RouterFunction<ServerResponse> routes(ProstitutaClienteHandler handler){

        return RouterFunctions.route(RequestPredicates.GET("/api/v1/prostitutas"),handler::listar);

    }
}
