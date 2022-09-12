package cz.palivtom.httpmonitoring.utils;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MonitoringEndpointRestCall implements Runnable {

    private final MonitoringEndpointResponseService monitoringEndpointResponseService;
    private final MonitoringEndpoint monitoringEndpoint;

    public MonitoringEndpointRestCall(MonitoringEndpointResponseService monitoringEndpointResponseService, MonitoringEndpoint monitoringEndpoint) {
        this.monitoringEndpointResponseService = monitoringEndpointResponseService;
        this.monitoringEndpoint = monitoringEndpoint;
    }

    @Override
    public void run() {
        ResponseEntity<String> restTemplate = new RestTemplate().exchange(monitoringEndpoint.getUrl(), monitoringEndpoint.getHttpMethod(), null, String.class);
        monitoringEndpointResponseService.saveResponse(new MonitoringEndpointResponse(monitoringEndpoint, restTemplate.getBody(), restTemplate.getStatusCodeValue()));
    }
}