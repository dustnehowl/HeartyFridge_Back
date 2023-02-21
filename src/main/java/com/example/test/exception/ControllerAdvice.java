package com.example.test.exception;

import com.example.test.exception.dto.BadRequestFailResponse;
import com.example.test.exception.dto.NotFoundFailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler({
            OnReservedFoodException.class,
            TooManyReservedException.class,
            NotTakerException.class,
            SameGiverTakerException.class,
            DuplicateBookmarkException.class
    })
    public ResponseEntity<BadRequestFailResponse> badRequest(Exception e) {
        return ResponseEntity.badRequest()
                .body(BadRequestFailResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({
            BookmarkNotFoundException.class
    })
    public ResponseEntity<NotFoundFailResponse> notFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(NotFoundFailResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
                );
    }


}
