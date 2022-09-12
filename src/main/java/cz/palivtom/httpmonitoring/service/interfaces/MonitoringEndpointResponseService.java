package cz.palivtom.httpmonitoring.service.interfaces;

import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;

import java.util.List;

public interface MonitoringEndpointResponseService {
    List<MonitoringEndpointResponse> getTop10(Long monitoringEndpointId);
    void saveResponse(MonitoringEndpointResponse response);
}