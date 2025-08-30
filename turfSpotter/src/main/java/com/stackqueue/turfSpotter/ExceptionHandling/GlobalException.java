package com.stackqueue.turfSpotter.ExceptionHandling;

import com.stackqueue.turfSpotter.POJO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> userNotFoundException(UserException userException){
        ServerResponse serverResponse = new ServerResponse(userException.getHttpCode(),userException.getResponseMessage(),userException.getDateTime());
        return new ResponseEntity<>(serverResponse, userException.getHttpCode());
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<?> customerNotFoundException(CustomerException customerException){
        ServerResponse serverResponse = new ServerResponse(customerException.getHttpCode(),customerException.getResponseMessage(),customerException.getDateTime());
        return new ResponseEntity<>(serverResponse, customerException.getHttpCode());
    }

    @ExceptionHandler(TurfOwnerException.class)
    public ResponseEntity<?> turfOwnerException(TurfOwnerException turfOwnerException){
        ServerResponse serverResponse = new ServerResponse(turfOwnerException.getHttpCode(),turfOwnerException.getResponseMessage(),turfOwnerException.getDateTime());
        return new ResponseEntity<>(serverResponse, turfOwnerException.getHttpCode());
    }

    @ExceptionHandler(TurfDetailsException.class)
    public ResponseEntity<?> turfDetailsException(TurfDetailsException turfDetailsException){
        ServerResponse serverResponse = new ServerResponse(turfDetailsException.getHttpCode(),turfDetailsException.getResponseMessage(),turfDetailsException.getDateTime());
        return new ResponseEntity<>(serverResponse, turfDetailsException.getHttpCode());
    }

}
