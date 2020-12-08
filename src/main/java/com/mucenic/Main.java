package com.mucenic;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        Handler handler = new Handler();
        System.out.println("Running...");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/first", handler);
        server.createContext("/second", handler);
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
