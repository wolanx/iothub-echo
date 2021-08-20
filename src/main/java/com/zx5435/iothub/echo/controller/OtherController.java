package com.zx5435.iothub.echo.controller;

import com.zx5435.iothub.echo.model.dao.DeviceDAO;
import com.zx5435.iothub.echo.model.db.DeviceDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zx5435
 */
@Controller
public class OtherController {

    @Resource
    DeviceDAO deviceDAO;

    @GetMapping("/")
    public String index(Model model) {
        List<DeviceDO> all = deviceDAO.findAll();
        model.addAttribute("devices", all);
        return "index";
    }

}
