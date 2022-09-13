package cz.palivtom.httpmonitoring.service.interfaces;

import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;

import java.util.List;

public interface MonitoringEndpointResponseService {

    /**
     * Wrapping the repository call
     *
     * @param monitoringEndpointId id of the parent element
     * @return list of the top ten founded responses
     */
    List<MonitoringEndpointResponse> getTop10(Long monitoringEndpointId);

    /**
     * Wrapping the repository call and also updating the parent element
     *
     * @param response entity to save
     */
    void saveResponse(MonitoringEndpointResponse response);
}