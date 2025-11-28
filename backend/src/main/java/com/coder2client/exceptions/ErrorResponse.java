package com.coder2client.exceptions;

public record ErrorResponse (
        int status,
        String message,
        String details
){
}
