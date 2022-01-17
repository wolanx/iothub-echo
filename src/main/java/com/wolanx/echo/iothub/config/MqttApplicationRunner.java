package com.wolanx.echo.iothub.config;

import com.wolanx.echo.iothub.broker.IotHubMqtt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wolanx
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
