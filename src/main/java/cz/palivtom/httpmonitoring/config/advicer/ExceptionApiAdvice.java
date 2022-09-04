package cz.palivtom.httpmonitoring.config.advicer;

import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeException;
import cz.palivtom.httpmonitoring.model.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionApiAdvice {

    @ExceptionHandler(value = ApiRuntimeException.class)
    private ResponseEntity<ExceptionDto> handleApiRuntimeException(ApiRuntimeException exception) {
        ExceptionDto exceptionDto = new ExceptionDto()
                .title(exception.getTitle())
                .message(exception.getMessage())
                .code(exception.getHttpStatus().value());
        return new ResponseEntity<>(exceptionDto, exception.getHttpStatus());
    }

}