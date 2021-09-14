package com.zx5435.echo.iothub.controller;

import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.dao.ProductDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import com.zx5435.echo.iothub.model.vo.DeviceVO;
import com.zx5435.echo.iothub.util.IdWorker;
import com.zx5435.jii.web.ApiData;
import com.zx5435.jii.web.WebException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zx5435
 */
@Controller
public class DeviceController {

    @Resource
    DeviceDAO deviceDAO;

    @Resource
    ProductDAO productDAO;

    @GetMapping("/device/{id}")
    public String info(Model model, @PathVariable long id) {
        DeviceDO deviceDO = deviceDAO.findById(id).orElseThrow(WebException::a404);
        DeviceVO deviceVO = new DeviceVO();
        BeanUtils.copyProperties(deviceDO, deviceVO);

        model.addAttribute("device", deviceVO);
        return "device_info";
    }

    @PostMapping("/api/device")
    @ResponseBody
    public ApiData create(@RequestBody DeviceDO device) {
        device.setId(IdWorker.nextId());
        device.setType(1);
        device = deviceDAO.save(device);
        return ApiData.success(device);
    }

    @DeleteMapping("/api/device/{id}")
    @ResponseBody
    public ApiData delete(@PathVariable long id) {
        deviceDAO.deleteById(id);
        return ApiData.success("Deleted");
    }

}
