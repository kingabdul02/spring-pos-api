package com.king.springposapi.demo;

import com.king.springposapi.exception.Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {
    private final Exception exception;
    @GetMapping
    public ResponseEntity<String>  sayHello(){
        return ResponseEntity.ok("Hello from secure endpoint");
    }

    @GetMapping("/error")
    public ResponseEntity<?>  error(){
        return ResponseEntity.ok(exception.throwException());
    }
}
