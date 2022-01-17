package com.wolanx.echo.iothub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolanx.jii.web.ApiData;
import com.wolanx.jii.web.ApiException;
import com.wolanx.jii.web.WebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;

/**
 * @author wolanx
 */
@Slf4j
@ControllerAdvice
public class ErrorConfig {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object defaultErrorHandler(Model m, HttpServletRequest request, HttpServletResponse resp, Exception e) throws IOException {
        log.error(e.getMessage());
        if ("*/*".equals(request.getHeader("accept"))) {
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            resp.setCharacterEncoding("utf-8");

            resp.setStatus(400);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(new ApiData(e.getMessage(), 400)));

            resp.flushBuffer();
            return null;
        } else {
            m.addAttribute("error", new WebException(e.getMessage(), 400));
            return "error";
        }
    }

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LinkedHashMap<String, Object> e400(ApiException e) {
        LinkedHashMap<String, Object> m = new LinkedHashMap<>();
        m.put("status", HttpStatus.BAD_REQUEST.value());
        m.put("code", e.getCode());
        m.put("message", e.getMessage());
        m.put("previous", e.getCause());

        return m;
    }

    @ExceptionHandler(WebException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String e400(Model m, WebException e) {
        m.addAttribute("error", e);
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiData handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        return new ApiData(e.getMessage(), 403);
    }

}
