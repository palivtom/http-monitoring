package cz.palivtom.httpmonitoring.controller;

import cz.palivtom.httpmonitoring.api.MonitoringEndpointApi;
import cz.palivtom.httpmonitoring.facade.interfaces.MonitoringEndpointFacade;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointDto;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointPageableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MonitoringEndpointController implements MonitoringEndpointApi {

    private final MonitoringEndpointFacade facade;

    @Override
    public ResponseEntity<MonitoringEndpointDto> createMonitoringEndpoint(MonitoringEndpointDto monitoringEndpointDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.create(monitoringEndpointDto));
    }

    @Override
    public ResponseEntity<MonitoringEndpointDto> deleteMonitoringEndpoint(Long monitoringEndpointId) {
        return ResponseEntity.ok(facade.delete(monitoringEndpointId));
    }

    @Override
    public ResponseEntity<MonitoringEndpointDto> editMonitoringEndpoint(Long monitoringEndpointId, MonitoringEndpointDto monitoringEndpointDto) {
        return ResponseEntity.ok(facade.update(monitoringEndpointDto, monitoringEndpointId));
    }

    @Override
    public ResponseEntity<MonitoringEndpointDto> getMonitoringEndpoint(Long monitoringEndpointId) {
        return ResponseEntity.ok(facade.get(monitoringEndpointId));
    }

    @Override
    public ResponseEntity<MonitoringEndpointPageableDto> getMonitoringEndpoints(Integer pageSize, Integer page, String filter, String order, Boolean validOnly) {
        return ResponseEntity.ok(facade.getAll(pageSize, page, filter, order, validOnly));
    }
}