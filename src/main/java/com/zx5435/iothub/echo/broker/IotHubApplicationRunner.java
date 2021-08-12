package com.zx5435.iothub.echo.broker;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zx5435
 */
@Component
public class IotHubApplicationRunner implements ApplicationRunner {

    @Resource
    IotHubMqtt iotHubMqtt;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iotHubMqtt.start();
    }

}
