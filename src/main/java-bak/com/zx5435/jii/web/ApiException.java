package com.zx5435.jii.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zx5435
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -4100309035717531908L;

    private int code = 1;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public static ApiException a404() {
        return new ApiException("Not found.", 404);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
