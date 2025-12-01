package com.filmestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por capturar e tratar exceções de toda a aplicação.
 *
 * Ela permite padronizar mensagens de erro e impedir que stacktraces
 * apareçam para o usuário final.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura erros do tipo NumberFormatException — comuns na leitura do CSV.
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> tratarErroDeNumero(NumberFormatException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro ao converter número: " + e.getMessage());
    }

    /**
     * Captura qualquer NullPointerException.
     * Muito útil em APIs.
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> tratarNullPointer(NullPointerException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Um erro inesperado ocorreu (NULL).");
    }

    /**
     * Captura qualquer exceção genérica não tratada.
     * Serve como fallback.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErroGenerico(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado: " + e.getMessage());
    }
}
