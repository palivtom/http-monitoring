package cz.palivtom.httpmonitoring.exception.runtime;

import org.slf4j.helpers.MessageFormatter;

public abstract class HttpMonitoringRuntimeException extends RuntimeException {
    protected HttpMonitoringRuntimeException(String message, Object... parameters) {
        super(formatMessage(message, parameters));
    }

    protected HttpMonitoringRuntimeException(String message, Throwable cause, Object... parameters) {
        super(formatMessage(message, parameters), cause);
    }

    private static String formatMessage(String message, Object... parameters) {
        return MessageFormatter.arrayFormat(message, parameters).getMessage();
    }
}