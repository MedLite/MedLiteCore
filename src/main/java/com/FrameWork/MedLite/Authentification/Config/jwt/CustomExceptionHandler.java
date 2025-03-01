/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Authentification.Config.jwt;
 
import com.FrameWork.MedLite.web.errors.IllegalBusinessLogiqueException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Administrator
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Unauthorized: You do not have permission to access this resource.");
        return ResponseEntity.badRequest().body(errorResponse);
    }

     @ExceptionHandler(IllegalBusinessLogiqueException.class)
    protected ResponseEntity<Object> handleIllegalBusinessLogiqueException(IllegalBusinessLogiqueException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("description", ex.getMessage()); // Use the message from your exception
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); // Or appropriate status
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("description", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }

//    // Add handlers for other exceptions as needed.  For example:
//    @ExceptionHandler(Exception.class) // Generic exception handler (use cautiously)
//    protected ResponseEntity<Object> handleGenericException(Exception ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("description", "An unexpected error occurred."); // Generic message
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR); // Only use 500 for truly unexpected errors.  Log the exception details for debugging.
//    }
}
