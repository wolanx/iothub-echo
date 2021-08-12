package com.zx5435.iothub.echo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zx5435
 */
@RestController
public class OtherController {

    @GetMapping("/")
    public String index() {
        return "Hi iothub-echo.";
    }

}
