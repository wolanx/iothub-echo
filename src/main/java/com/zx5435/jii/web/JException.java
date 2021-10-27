package com.zx5435.jii.web;

/**
 * @author zx5435
 */
public class JException extends RuntimeException {

    private static final long serialVersionUID = -4100309035717531908L;

    private int status = 400;
    private int code = 1;
    private String message;

    public JException(String message) {
        super(message);
    }

    public JException(String message, int code, int status) {
        super(message);
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static JException a404() {
        return new JException("Not found.", 0, 403);
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
