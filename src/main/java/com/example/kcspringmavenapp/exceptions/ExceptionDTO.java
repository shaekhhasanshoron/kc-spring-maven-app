package com.example.kcspringmavenapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {
    private String name;
    private HttpStatus status;
    private String message;
}
