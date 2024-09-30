package com.example.etaskify.exception;

import com.example.etaskify.dto.response.ExceptionResponse;
import lombok.Getter;

@Getter

public class ApplicationException extends RuntimeException{
    private final ExceptionResponse response;

    public ApplicationException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }
    public static ApplicationException of(ExceptionResponse response){
        return new ApplicationException(response);
    }

}
