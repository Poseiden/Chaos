package com.poseiden.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.ProtocolException;

@Getter
@AllArgsConstructor
public class BusinessException extends ProtocolException {
    private ErrorKey errorKey;
    private HttpStatus httpStatus;
}
