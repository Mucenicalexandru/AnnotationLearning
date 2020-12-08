package com.mucenic;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Handler implements HttpHandler {


    @SneakyThrows
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //here we have the methods for different paths
        Route route = new Route();

        //the response is empty initially
        String response = "...";

        //what the user types in the browser
        String currentPath = exchange.getRequestURI().getPath();
        System.out.println("Actual path: " + currentPath);


        //we iterate through the methods from Route using Reflection API
        for(Method method : Route.class.getMethods()){
            if(method.isAnnotationPresent((WebRoute.class))){ //if annotation is present then =>
                //we check if the current path(which user types) is equal to the path annotated in the method
                if(currentPath.equals(method.getAnnotation(WebRoute.class).path())){
                    response = (String) method.invoke(route);
                    break;
                }else{
                    response = route.secondPage();
                    break;
                }
            }
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
