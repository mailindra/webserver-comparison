package com.mailindra.server;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static io.undertow.util.Headers.CONTENT_TYPE;

public class GreetingWebServer {
    static HttpHandler greetingHandler() {
        var content = "Hello, People";
        var contentBytes = content.getBytes(StandardCharsets.UTF_8);
        var buffer = ByteBuffer.allocateDirect(contentBytes.length)
                .put(contentBytes)
                .flip();

        return exchange -> {
            exchange.getResponseHeaders().put(CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send(buffer.duplicate());
        };
    }

    public static void main(String[] args) {
        Undertow.builder()
                .addHttpListener(8080,"0.0.0.0" )
                .setIoThreads(Runtime.getRuntime().availableProcessors() * 2)
                .setServerOption(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false)
                .setHandler(new PathHandler().addExactPath("/", greetingHandler()))
                .build()
                .start();
    }

}