package com.mailindra.server;


import org.rapidoid.config.Conf;
import org.rapidoid.http.MediaType;
import org.rapidoid.setup.App;
import org.rapidoid.setup.On;

public class Main {

    public static void main(String[] args) {
        final String content = "Hello, People";
        App.run(args);
        Conf.HTTP.set("maxPipeline", 128);
        Conf.HTTP.set("timeout", 0);
        Conf.HTTP.sub("mandatoryHeaders").set("connection", false);
        On.port(8080);
        On.get("/")
                .managed(false)
                .contentType(MediaType.TEXT_PLAIN)
                .serve(content);
    }

}