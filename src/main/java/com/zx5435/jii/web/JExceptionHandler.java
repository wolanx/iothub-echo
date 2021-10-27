package com.zx5435.jii.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JExceptionHandler implements ExceptionMapper<JException> {

    @Override
    @SneakyThrows
    public Response toResponse(JException ex) {
        String s = new ObjectMapper().writeValueAsString(new ApiData(ex.getMessage(), ex.getStatus()));
        System.out.println("s = " + s);
        return Response.status(ex.getStatus()).entity(s).type(MediaType.APPLICATION_JSON).build();
    }

}
