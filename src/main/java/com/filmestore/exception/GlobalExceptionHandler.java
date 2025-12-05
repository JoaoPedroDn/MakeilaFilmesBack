package com.filmestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> tratarErroDeNumero(NumberFormatException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro ao converter número: " + e.getMessage());
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> tratarNullPointer(NullPointerException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Um erro inesperado ocorreu (NULL).");
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErroGenerico(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado: " + e.getMessage());
    }
}
