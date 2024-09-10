package com.example.swager.exceptions;


import com.example.swager.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex, HttpServletRequest request){
        ApiResponse response=ApiResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("an unexpected error occurred: "+ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex){
        StringBuilder errrMessage=new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error->{
            errrMessage.append(error.getDefaultMessage()).append(";");
        }));
        ApiResponse response=ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed"+errrMessage.toString())
                .data(null)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse response=ApiResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Resource not found: "+ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
   }



}