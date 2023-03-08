package com.king.springposapi.exception;

import org.springframework.stereotype.Service;

@Service
public class Exception {
    public String throwException(){
        throw new IllegalStateException("Something went wrong");
    }
}
