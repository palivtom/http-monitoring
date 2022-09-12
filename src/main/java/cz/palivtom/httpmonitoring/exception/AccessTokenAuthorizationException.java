package cz.palivtom.httpmonitoring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AccessTokenAuthorizationException extends HttpMonitoringException {
    private final HttpStatus httpStatus;
    private final String title;

    public AccessTokenAuthorizationException(String message, Object... parameters) {
        super(message, parameters);
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.title = HttpStatus.UNAUTHORIZED.getReasonPhrase();
    }
}