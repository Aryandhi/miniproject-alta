package com.alta.miniprojectcheckpoint.util;

import com.alta.miniprojectcheckpoint.domain.common.ApiResponse;
import com.alta.miniprojectcheckpoint.model.Departement;
import com.alta.miniprojectcheckpoint.model.Employee;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseUtilEmployee {
    private ResponseUtilEmployee() {}

    public static <T> Employee build(String message, T data, HttpStatus httpStatus) {
        return new Employee(build(message, data), httpStatus);
    }

    private static <T> ApiResponse<T> build(String message, T data) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .data(data)
                .build();
    }
}
