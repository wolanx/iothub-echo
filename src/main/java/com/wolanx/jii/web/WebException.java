package com.wolanx.jii.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wolanx
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WebException extends RuntimeException {

    private static final long serialVersionUID = -4100309035717531908L;

    private int code = 1;

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, int code) {
        super(message);
        this.code = code;
    }

    public static WebException a404() {
        return new WebException("Not found.", 404);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
