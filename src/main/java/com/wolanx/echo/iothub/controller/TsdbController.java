package com.wolanx.echo.iothub.controller;

import com.wolanx.echo.iothub.manager.InfluxdbManager;
import com.wolanx.jii.web.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wolanx
 */
@Slf4j
@RestController
public class TsdbController {

    @Resource
    InfluxdbManager influxdbManager;

    @GetMapping("/api/tsdb/r")
    public ApiData read() {
        List<Object> arr = influxdbManager.queryBySql("SELECT * FROM sensor where sn='iot-echo-903-913332' order by time desc limit 3 tz('Asia/Shanghai')");
        return ApiData.success(arr);
    }

}
