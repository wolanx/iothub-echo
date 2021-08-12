package com.zx5435.iothub.echo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zx5435
 */
@SpringBootApplication
public class IotHubTest {

    public static void main(String[] args) {
        new SpringApplicationBuilder(IotHubTest.class).run(args);
    }

}
