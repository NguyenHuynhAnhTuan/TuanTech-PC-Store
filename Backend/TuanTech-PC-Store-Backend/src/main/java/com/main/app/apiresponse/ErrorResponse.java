package com.main.app.apiresponse;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Integer statusCode;
    private String message;
    private String timestamp;
    private String path;

    public ErrorResponse(int statusCode , String message , String path) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
        this.path = path;
    }
}
