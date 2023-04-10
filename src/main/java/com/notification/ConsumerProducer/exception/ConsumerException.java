package com.notification.ConsumerProducer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConsumerException extends RuntimeException {
    public ConsumerException(String msg, Throwable t) {
        super(msg, t);
    }

    public ConsumerException(String msg) {
        super(msg);
    }

}