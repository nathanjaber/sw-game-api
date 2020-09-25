package com.nathan.swplanets.exception;

import lombok.Data;

@Data
public class ExceptionDTO {
    private String message;

    public ExceptionDTO(String message) {
        this.message = message;
    }
}
