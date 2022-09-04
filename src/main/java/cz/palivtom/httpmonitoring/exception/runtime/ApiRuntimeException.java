package cz.palivtom.httpmonitoring.exception.runtime;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRuntimeException extends HttpMonitoringRuntimeException {

    private final String title;
    private final HttpStatus httpStatus;

    public ApiRuntimeException(HttpStatus httpStatus, String message, Object... parameters) {
        super(message, parameters);
        this.title = httpStatus.getReasonPhrase();
        this.httpStatus = httpStatus;
    }
}