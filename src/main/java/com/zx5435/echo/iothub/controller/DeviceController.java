package com.zx5435.echo.iothub.controller;

import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import com.zx5435.jii.web.WebException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
public class DeviceController {

    @Inject
    DeviceDAO deviceDAO;

    @GET
    @Path("/{id}")
    public String info(@PathParam("id") long id) {
        DeviceDO deviceDO = deviceDAO.findById(id).orElseThrow(WebException::a404);
        System.out.println("asd = " + deviceDO);
        return "Hello RESTEasy" + id;
    }

}
