package com.mucenic;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface WebRoute{
    String path();
    String method();
}

public class Routes {

    @WebRoute(path = "/first", method = "GET")
    public String firstPage(){
        return "This is the first page";
    }

    @WebRoute(path = "second", method = "GET")
    public String secondPage(){
        return "This is the second page";
    }
}
