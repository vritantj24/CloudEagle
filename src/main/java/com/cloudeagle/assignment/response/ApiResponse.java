package com.cloudeagle.assignment.response;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean status;
    private String message;
    private Object data;
}