package com.king.springposapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
public class ObjectNotValidException extends RuntimeException{
    private final Set<String> errorMessages;
}
