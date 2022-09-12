package cz.palivtom.httpmonitoring.config.advicer;

import cz.palivtom.httpmonitoring.exception.AccessTokenAuthorizationException;
import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionApiAdvice {

    @ExceptionHandler(value = ApiRuntimeRuntimeException.class)
    private ResponseEntity<ExceptionDto> handleApiRuntimeException(ApiRuntimeRuntimeException exception) {
        ExceptionDto exceptionDto = new ExceptionDto()
                .title(exception.getTitle())
                .message(exception.getMessage())
                .code(exception.getHttpStatus().value());
        return new ResponseEntity<>(exceptionDto, exception.getHttpStatus());
    }

    @ExceptionHandler(value = AccessTokenAuthorizationException.class)
    private ResponseEntity<ExceptionDto> handleApiRuntimeException(AccessTokenAuthorizationException exception) {
        ExceptionDto exceptionDto = new ExceptionDto()
                .title(exception.getTitle())
                .message(exception.getMessage())
                .code(exception.getHttpStatus().value());
        return new ResponseEntity<>(exceptionDto, exception.getHttpStatus());
    }
}