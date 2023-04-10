package com.notification.ConsumerProducer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProducerException extends RuntimeException {
    public ProducerException(String msg, Throwable t) {
        super(msg, t);
    }

    public ProducerException(String msg) {
        super(msg);
    }

}