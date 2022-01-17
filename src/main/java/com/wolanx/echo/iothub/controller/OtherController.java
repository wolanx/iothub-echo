package com.wolanx.echo.iothub.controller;

import com.wolanx.echo.iothub.model.dao.DeviceDAO;
import com.wolanx.echo.iothub.model.db.DeviceDO;
import com.wolanx.echo.iothub.model.vo.DeviceVO;
import com.wolanx.echo.iothub.service.DeviceStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wolanx
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
