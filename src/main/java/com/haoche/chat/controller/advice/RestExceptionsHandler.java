package com.haoche.chat.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.haoche.chat.controller")
public class RestExceptionsHandler {
    private Logger logger = LoggerFactory.getLogger(RestExceptionsHandler.class);

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    protected Template handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);

        Template template = new Template();
        template.setResult(ErrorCodeMessage.MethodNotSupported);
        return template;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    protected Template handleException(Exception e) {
        logger.error(e.getMessage(), e);

        Template template = new Template();
        template.setResult(ErrorCodeMessage.UnknownException);
        template.setMessage(e.getMessage());
        return template;
    }
}

