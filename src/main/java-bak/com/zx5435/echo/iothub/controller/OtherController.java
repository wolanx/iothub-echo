package com.zx5435.echo.iothub.controller;

import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import com.zx5435.echo.iothub.model.vo.DeviceVO;
import com.zx5435.echo.iothub.service.DeviceStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx5435
 */
@Controller
public class OtherController {

    @Resource
    DeviceDAO deviceDAO;

    @Resource
    DeviceStateService deviceStateService;

    @GetMapping("/")
    public String index(Model model) {
        List<DeviceDO> res = deviceDAO.findAll();

        List<DeviceVO> all = new ArrayList<>();
        for (DeviceDO one : res) {
            DeviceVO vo = new DeviceVO();
            vo.setIsOnline(deviceStateService.isOnline(one.getDeviceName() + "&" + one.getProductKey()));
            BeanUtils.copyProperties(one, vo);
            all.add(vo);
        }

        model.addAttribute("devices", all);
        return "index";
    }

}
