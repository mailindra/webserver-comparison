package com.mailindra.javaSpringWebflux.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GreetingResource {
    private static final String content = "Hello, People";

    @GetMapping("/")
    public Mono<String> greeting() {
        return Mono.just(content);
    }

}
