package com.zx5435;

import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import com.zx5435.echo.iothub.model.vo.DeviceVO;
import com.zx5435.echo.iothub.service.DeviceStateService;
import io.quarkus.qute.TemplateInstance;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class OtherController {

    @Inject
    DeviceDAO deviceDAO;

    @Inject
    DeviceStateService deviceStateService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @SneakyThrows
    @GET
    @Path("/idx")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        List<DeviceDO> res = deviceDAO.findAll();
        List<DeviceVO> all = new ArrayList<>();
        for (DeviceDO one : res) {
            DeviceVO vo = new DeviceVO();
            vo.setIsOnline(deviceStateService.isOnline(one.getDeviceName() + "&" + one.getProductKey()));
            BeanUtils.copyProperties(vo, one);
            all.add(vo);
        }
        return Templates.index(all);
    }

}