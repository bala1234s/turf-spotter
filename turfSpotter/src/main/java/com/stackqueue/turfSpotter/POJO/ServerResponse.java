package com.stackqueue.turfSpotter.POJO;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ServerResponse {
    private HttpStatus httpCode;
    private String responseMessage;
    private LocalDate dateTime;

    public ServerResponse(HttpStatus httpCode, String responseMessage, LocalDate dateTime) {
        this.httpCode = httpCode;
        this.responseMessage = responseMessage;
        this.dateTime = dateTime;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }
}
