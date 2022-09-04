package cz.palivtom.httpmonitoring.controller;

import cz.palivtom.httpmonitoring.api.MonitoringEndpointsApi;
import cz.palivtom.httpmonitoring.mapper.MonitoringEndpointMapper;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointDto;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointPageableDto;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MonitoringEndpointController implements MonitoringEndpointsApi {

    private final MonitoringEndpointMapper monitoringEndpointMapper;
    private final MonitoringEndpointService monitoringEndpointService;

    @Override
    public ResponseEntity<MonitoringEndpointDto> createMonitoringEndpoint(MonitoringEndpointDto monitoringEndpointDto) {
        return null; //todo
    }

    @Override
    public ResponseEntity<Void> deleteMonitoringEndpoint(Long monitoringEndpointId) {
        return null; //todo
    }

    @Override
    public ResponseEntity<MonitoringEndpointDto> editMonitoringEndpoint(Long monitoringEndpointId, MonitoringEndpointDto monitoringEndpointDto) {
        return null; //todo
    }

    @Override
    public ResponseEntity<MonitoringEndpointDto> getMonitoringEndpoint(Long monitoringEndpointId) {
        return null; //todo
    }

    @Override
    public ResponseEntity<MonitoringEndpointPageableDto> getMonitoringEndpoints(Integer pageSize, Integer page, String filter, String order, Boolean validOnly) {
        return null; //todo
    }
}