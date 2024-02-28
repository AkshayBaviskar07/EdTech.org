package org.telusko.course.TeluskoApp.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.telusko.course.TeluskoApp.exceptions.CourseNotFoundException;
import org.telusko.course.TeluskoApp.model.ErrorDetails;

import java.time.LocalDate;
import java.time.LocalTime;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCourseNotFoundException(CourseNotFoundException exception){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), LocalDate.now(), LocalTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception exception){
        ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), LocalDate.now(), LocalTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
