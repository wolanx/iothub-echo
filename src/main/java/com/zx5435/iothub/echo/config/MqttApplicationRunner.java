package com.zx5435.iothub.echo.config;

import com.zx5435.iothub.echo.broker.IotHubMqtt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zx5435
 */
@Component
public class MqttApplicationRunner implements ApplicationRunner {

    @Resource
    IotHubMqtt iotHubMqtt;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iotHubMqtt.start();
    }

}
