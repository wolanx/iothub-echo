package com.wolanx.echo.iothub;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author wolanx
 */
@SpringBootApplication
public class IotHubApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(IotHubApplication.class).run(args);
    }

}
