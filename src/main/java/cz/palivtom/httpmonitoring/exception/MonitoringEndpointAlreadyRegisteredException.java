package cz.palivtom.httpmonitoring.exception;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class MonitoringEndpointAlreadyRegisteredException extends HttpMonitoringException {

    private final MonitoringEndpoint monitoringEndpoint;

    public MonitoringEndpointAlreadyRegisteredException(@NonNull MonitoringEndpoint monitoringEndpoint) {
        super("Cannot register monitoring endpoint with ID '{}'. Endpoint is already registered.", monitoringEndpoint.getId());
        this.monitoringEndpoint = monitoringEndpoint;
    }
}