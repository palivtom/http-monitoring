package cz.palivtom.httpmonitoring.exception;

import org.slf4j.helpers.MessageFormatter;

public abstract class HttpMonitoringException extends Exception {
    protected HttpMonitoringException(String message, Object... parameters) {
        super(formatMessage(message, parameters));
    }

    protected HttpMonitoringException(String message, Throwable cause, Object... parameters) {
        super(formatMessage(message, parameters), cause);
    }

    private static String formatMessage(String message, Object... parameters) {
        return MessageFormatter.arrayFormat(message, parameters).getMessage();
    }
}