package com.example.cgnjava243restclient.exceptions;

import com.example.cgnjava243restclient.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
//@RestControllerAdvice kennzeichnet diese Klasse als GlobalerControllerAdvisor,
// also werden seine ExceptionHandler auf euer gesamtes Projekt angewendet
@RestControllerAdvice
public class GlobalExceptionHandler {
    //@ExceptionHandler() kennzeichnet eine Methode die eine bestimmte Exceptionart abfangen soll
    // und die nachfolgende Methode als "Plan B" ausführt damit eine korrekte Kommunikation
    // mit dem Client stattfinden kann
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Minimalster ExceptionHandler
//    @ExceptionHandler(InvalidIdException.class)
//    public ResponseEntity<String> handleInvalidIdException(InvalidIdException exception){
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//    }

    //ExceptionHandler mit mehr Informationen zum Fehler
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidIdException(InvalidIdException exception,
                                                                     WebRequest webRequest){
    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
            webRequest.getDescription(false),
            HttpStatus.NOT_FOUND,
            exception.getMessage(),
            LocalDateTime.now()
    );
    return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

}
