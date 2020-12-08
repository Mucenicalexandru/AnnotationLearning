package com.mucenic;


public class Route {

    private String name;
    private int age;


    @WebRoute(path = "/first")
    public String firstPage(){
        return "This is the response from the first page";
    }

    @WebRoute(path = "/second")
    public String secondPage(){
        return "This is the response from the second page";
    }
}

