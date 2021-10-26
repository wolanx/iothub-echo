package com.zx5435.echo.iothub.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class TestController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("/index")
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello qweqweqweqwe";
    }
}