package com.stackqueue.turfSpotter.POJO;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class TurfDetailsException extends RuntimeException{

        private HttpStatus httpCode;
        private String responseMessage;
        private LocalDate dateTime;

        public TurfDetailsException(HttpStatus httpCode, String responseMessage, LocalDate dateTime) {
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
