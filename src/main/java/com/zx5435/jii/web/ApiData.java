package com.zx5435.jii.web;

/**
 * @author zx5435
 */
public class ApiData {

    public int status = 200;
    public int code = 0;
    public String message;
    public Object data;

    public ApiData(String message) {
        this.message = message;
    }

    public ApiData(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static ApiData success(String message) {
        return new ApiData(message);
    }

    public static ApiData success(Object data) {
        ApiData obj = new ApiData("success");
        obj.data = data;
        return obj;
    }

}
