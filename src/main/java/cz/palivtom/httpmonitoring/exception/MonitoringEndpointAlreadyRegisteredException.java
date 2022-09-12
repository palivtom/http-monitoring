package cz.palivtom.httpmonitoring.exception;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import lombok.Getter;

@Getter
public class MonitoringEndpointAlreadyRegisteredException extends HttpMonitoringException {

    private final MonitoringEndpoint monitoringEndpoint;

    public MonitoringEndpointAlreadyRegisteredException(MonitoringEndpoint monitoringEndpoint) {
        super("Cannot register monitoring endpoint with ID '{}'. Endpoint is already registered.", monitoringEndpoint.getId());
        this.monitoringEndpoint = monitoringEndpoint;
    }
}