package com.example.PokemonAPI.exception;

import com.example.PokemonAPI.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException ex) {
        List<String> detalles= new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            detalles.add(error.getField() + ":" + error.getDefaultMessage());
        }
        ErrorDTO errorDTO = new ErrorDTO("Error en la validación de los datos de entrada", detalles,
                LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> manejarRecursoNoEncontrado(ResourceNotFoundException ex) {
        List<String> detalles= new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO("El recurso solicitado no fue encontrado.", detalles,
                LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorDTO> manejarReglasDeNegocio(BusinessRuleException ex) {
        List<String> detalles= new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(
                "Se ha incumplido una regla de negocio.",
                detalles,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> errorDelProgramador(Exception ex) {

        List<String> detalles = new ArrayList<>();
        detalles.add("Ocurrio un error inesperado al procesar la solicitud.");

        ErrorDTO errorDTO = new ErrorDTO(
                "Error interno del servidor.",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                errorDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Maneja errores al interpretar el JSON recibido.
     *
     * Por ejemplo, cuando un dato tiene un tipo incompatible
     * con el atributo correspondiente en Pokemon.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> manejarJsonInvalido(
            HttpMessageNotReadableException ex) {

        List<String> detalles = new ArrayList<>();
        detalles.add("Verifique el formato y los tipos de datos enviados.");

        ErrorDTO errorDTO = new ErrorDTO(
                "No se pudo interpretar el JSON recibido.",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                errorDTO,
                HttpStatus.BAD_REQUEST
        );
    }


}
