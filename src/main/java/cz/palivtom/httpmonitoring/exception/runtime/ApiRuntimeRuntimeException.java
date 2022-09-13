package cz.palivtom.httpmonitoring.exception.runtime;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRuntimeRuntimeException extends HttpMonitoringRuntimeException {

    private final String title;
    private final HttpStatus httpStatus;

    public ApiRuntimeRuntimeException(@NonNull HttpStatus httpStatus, String message, Object... parameters) {
        super(message, parameters);
        this.title = httpStatus.getReasonPhrase();
        this.httpStatus = httpStatus;
    }
}