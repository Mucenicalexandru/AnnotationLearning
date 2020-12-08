package com.mucenic;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class MiniWebServer {

    public static void main(String[] args) throws Exception {
        System.out.println("Running...");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/another", new AnotherHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "This is the response";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            //Ascii code from the response String
            os.write(response.getBytes());
            os.close();
        }
    }


    //example with a JSON response
    static class AnotherHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{ \"contacts\": [ { \"id\": \"c200\", \"name\": \"Mucenic Alexandru\", \"email\": \"alex@gmail.com\", \"address\": \"xx-xx-xxxx,x - street, x - country\", \"gender\" : \"male\", \"phone\": { \"mobile\": \"+91 0000000000\", \"home\": \"00 000000\", \"office\": \"00 000000\" } }, { \"id\": \"c201\", \"name\": \"Johnny Depp\", \"email\": \"johnny_depp@gmail.com\", \"address\": \"xx-xx-xxxx,x - street, x - country\", \"gender\" : \"male\", \"phone\": { \"mobile\": \"+91 0000000000\", \"home\": \"00 000000\", \"office\": \"00 000000\" } } ] }";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }


    }
}
