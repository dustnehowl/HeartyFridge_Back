package com.example.test.exception;

import com.example.test.exception.auth.ExpiredException;
import com.example.test.exception.auth.IllegalException;
import com.example.test.exception.auth.MalformedException;
import com.example.test.exception.auth.UnsupportedException;
import com.example.test.exception.dto.BadRequestFailResponse;
import com.example.test.exception.dto.NotFoundFailResponse;
import com.example.test.exception.dto.UnauthorizedFailResponse;
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
            ExpiredException.class,
            IllegalException.class,
            UnsupportedException.class,
            MalformedException.class
    })
    public ResponseEntity<UnauthorizedFailResponse> unauthorized(Exception e) {
        System.out.println("여기들어와야하는디...;;;");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(UnauthorizedFailResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
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
