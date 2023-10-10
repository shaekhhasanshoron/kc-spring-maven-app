package com.example.kcspringmavenapp.exceptions.classes;

import java.io.Serial;

public class ApiException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public ApiException(String message) {
        super(message);
    }
}
