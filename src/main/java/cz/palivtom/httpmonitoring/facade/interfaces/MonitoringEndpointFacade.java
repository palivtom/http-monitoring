package cz.palivtom.httpmonitoring.facade.interfaces;

import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointDto;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointPageableDto;

public interface MonitoringEndpointFacade {
    MonitoringEndpointDto create(MonitoringEndpointDto monitoringEndpointDto);
    MonitoringEndpointDto delete(Long monitoringEndpointId);
    MonitoringEndpointDto update(MonitoringEndpointDto monitoringEndpointDto, Long monitoringEndpointId);
    MonitoringEndpointDto get(Long monitoringEndpointId);
    MonitoringEndpointPageableDto getAll(Integer pageSize, Integer page, String filter, String order, Boolean validOnly);
}