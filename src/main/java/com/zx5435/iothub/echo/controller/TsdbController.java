package com.zx5435.iothub.echo.controller;

import com.zx5435.iothub.echo.manager.InfluxdbManager;
import com.zx5435.jii.web.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zx5435
 */
@Slf4j
@RestController
public class TsdbController {

    @Resource
    InfluxdbManager influxdbManager;

    @GetMapping("/api/tsdb/r")
    public ApiData read() {
        List<Object> arr = influxdbManager.read("SELECT * FROM sensor");
        return ApiData.success(arr);
    }

}
