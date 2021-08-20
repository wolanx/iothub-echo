package com.zx5435.iothub.echo.controller;

import com.zx5435.iothub.echo.model.dao.DeviceDAO;
import com.zx5435.iothub.echo.model.db.DeviceDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zx5435
 */
@Controller
public class DeviceController {

    @Resource
    DeviceDAO deviceDAO;

    @GetMapping("/device/{id}")
    public String info(Model model, @PathVariable long id) {
        Optional<DeviceDO> deviceDO = deviceDAO.findById(id);

        model.addAttribute("device", deviceDO.get());
        return "device_info";
    }

}
