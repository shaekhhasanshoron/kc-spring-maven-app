package com.example.kcspringmavenapp.utilities;

import com.example.kcspringmavenapp.exceptions.ExceptionDTO;
import com.example.kcspringmavenapp.exceptions.classes.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseData {
    public static <T> ResponseEntity<?> sendData(HttpStatus status, String message, T data) {
        return ResponseEntity.ok(Map.of("status", status.name(), "message", message, "data", data));
    }

    public static <T> ResponseEntity<?> sendData(T data) {
        return ResponseEntity.ok(data);
    }

    public static <T> ResponseEntity<?> sendErrorResponse(String data) throws ApiException {
        try {
            if (MessageNotFound(data)) {
                throw new ApiException("Invalid Input");
            }
            String[] strings = data.split(",");
            String[] split = strings[2].split(":");
            String message = split[1].replace("}}", "")
                    .replaceFirst("\"\"", "")
                    .replaceFirst("\"", "");
            throw new ApiException(message);
        } catch (Exception exception) {
            throw new ApiException(exception.getMessage());
        }
    }

    public static <T> ResponseEntity<?> sendErrorOutput(String data) throws ApiException {
        try {
            String[] dataMap = data.split(":", 2);
            HashMap<String, String> bodyMap = (HashMap<String, String>) Arrays.stream(dataMap[1].split(",")).map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0].trim().replaceAll("\"", "").replace("{", "").replace("}", ""), e -> e[1].trim().replaceAll("\"", "").replace("{", "").replace("}", "")));
            if (bodyMap.get("data").equals("null")) bodyMap.put("data", "");


            return ResponseEntity.status(HttpStatus.valueOf(Integer.parseInt(dataMap[0].trim()))).body(Map.of("error",
                    ExceptionDTO.builder()
                            .name("Exception")
                            .status(HttpStatus.valueOf(Integer.parseInt(dataMap[0].trim())))
                            .message(bodyMap.get("message"))
                            .build()));

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(Map.of("error",
                    ExceptionDTO.builder()
                            .name("Exception")
                            .status(HttpStatus.valueOf(400))
                            .message("Something went wrong")
                            .build()));
        }
    }

    public static boolean MessageNotFound(String data) {
        if (data.contains("[") && data.contains("]")) {
            return true;
        }
        return false;
    }
}
