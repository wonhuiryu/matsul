package com.wonhui.advice;

import com.wonhui.exception.ServiceException;
import com.wonhui.response.ResponseBaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wonhuiryu on 2018-05-12.
 */
//컨트롤러 예외시 처리할 광역 익셉션 핸들러
//서비스(컴퍼넌트)단계의 익셉션은 처리하지 못하여 따로 Preconfitions로 모듈화
@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity handleServiceException(ServiceException e) {
        return ResponseBaseDto.serviceError(e);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException manve) {
        FieldError fieldError = manve.getBindingResult().getFieldError();
        return ResponseBaseDto.badRequest(
                MethodArgumentNotValidExceptionMessage.builder()
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        .defaultMessage(fieldError.getDefaultMessage())
                        .build()
        );
    }

    @Getter
    @Builder
    public static class MethodArgumentNotValidExceptionMessage {
        private String field;
        private Object rejectedValue;
        private String defaultMessage;
    }
}
