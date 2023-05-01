package com.mailindra.server;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static io.undertow.util.Headers.CONTENT_TYPE;

public class GreetingWebServer {
    private static HttpHandler greetingHandler() {
        var content = "Hello, People".getBytes(StandardCharsets.UTF_8);
        var buffer = ByteBuffer.allocateDirect(content.length)
                .put(content)
                .flip();

        return exchange -> {
            exchange.getResponseHeaders().put(CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send(buffer.duplicate());
        };
    }
    private static HttpHandler pathHandler() {
        return new PathHandler()
                .addExactPath("/", greetingHandler());
    }

    public static void main(String[] args) {
        Undertow.builder()
                .addHttpListener(8080,"0.0.0.0" )
                .setIoThreads(Runtime.getRuntime().availableProcessors() * 2)
                .setServerOption(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false)
                .setHandler(pathHandler())
                .build()
                .start();
    }

}