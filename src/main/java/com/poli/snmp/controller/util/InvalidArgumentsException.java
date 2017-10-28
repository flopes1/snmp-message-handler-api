package com.poli.snmp.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidArgumentsException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public InvalidArgumentsException(String message)
    {
        super(message);
    }

    public InvalidArgumentsException()
    {
        super();
    }
}
