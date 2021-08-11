package com.zx5435.iothub.echo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zx5435
 */
@SpringBootApplication
public class IotHubApplication {

    public static void main(String[] args) {
        IotHubServer server = new IotHubServer();
        server.start();

        new SpringApplicationBuilder(IotHubApplication.class).run(args);
    }

}
