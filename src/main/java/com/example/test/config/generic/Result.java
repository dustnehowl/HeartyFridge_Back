package com.example.test.config.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private final int status = 200;
    private final String message = "SUCCESS";
    private T data;
}
