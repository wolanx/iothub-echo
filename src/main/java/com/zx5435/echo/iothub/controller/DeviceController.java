package com.zx5435.echo.iothub.controller;

import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import com.zx5435.echo.iothub.util.IdWorker;
import com.zx5435.jii.web.ApiData;
import com.zx5435.jii.web.JException;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceController {

    @Inject
    DeviceDAO deviceDAO;

    @Inject
    Template device_info;

    @GET
    @Path("/{id}")
    public TemplateInstance info(@PathParam("id") long id) {
        DeviceDO deviceDO = deviceDAO.findById(id).orElseThrow(JException::a404);
        System.out.println("device = " + deviceDO.getDeviceName());
        return device_info.data("device", deviceDO);
    }

    @POST
    @Path("/api/device")
    public ApiData create(DeviceDO device) {
        // todo camel
        device.setId(IdWorker.nextId());
        device.setType(1);
        device = deviceDAO.save(device);
        return ApiData.success(device);
    }

    @DELETE
    @Path("/api/device/{id}")
    public ApiData delete(@PathParam("id") long id) {
        deviceDAO.deleteById(id);
        return ApiData.success("Deleted");
    }

}
