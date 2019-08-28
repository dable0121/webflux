package com.dable.webfluxdemo.com.config;

import com.alibaba.fastjson.JSON;
import com.dable.webfluxdemo.com.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class WebFluxConfigration {

    @Bean
    public RouterFunction helloFunction() {
        return RouterFunctions.route().GET("/webflux", accept(MediaType.TEXT_PLAIN),
                serverRequest -> ServerResponse.ok().body(Mono.just("hello"), String.class)).build();
    }

    @Bean
    public RouterFunction userFunction() {
        return RouterFunctions.route().POST("/webflux/user", accept(MediaType.APPLICATION_JSON_UTF8),
                serverRequest -> ServerResponse.ok().body(serverRequest.bodyToMono(User.class).map(JSON::toJSONString),
                        String.class)).build();
    }
}
