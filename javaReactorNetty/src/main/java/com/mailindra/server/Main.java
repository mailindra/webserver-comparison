package com.mailindra.server;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

public class Main {
    private static final String content ="Hello, People";
    public static void main(String[] args) {
        HttpServer server =
                HttpServer.create()
                        .port(8080)
                        .route(r -> r.get("/",
                                (req, res) -> res.header(CONTENT_TYPE, TEXT_PLAIN)
                                        .sendString(Mono.just(content))));

        server.bindNow()
                .onDispose()
                .block();
    }
}