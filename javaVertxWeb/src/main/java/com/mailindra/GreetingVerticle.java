package com.mailindra;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;

public class GreetingVerticle extends AbstractVerticle {
    static final String content = "Hello, People";

    @Override
    public void start(Promise<Void> startPromise) {
        final Router app = Router.router(vertx);
        app.get("/").handler(ctx -> {
            ctx.response()
                    .putHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
                    .end(content);
        });

        vertx.createHttpServer().requestHandler(app).listen(8080, listen -> {
            if (listen.failed()) {
                listen.cause().printStackTrace();
                System.exit(1);
            }
            startPromise.complete();
        });

    }
}
