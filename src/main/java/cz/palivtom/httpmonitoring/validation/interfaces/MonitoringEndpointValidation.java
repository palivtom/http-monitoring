package cz.palivtom.httpmonitoring.validation.interfaces;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;

public interface MonitoringEndpointValidation {
    void onCreate(MonitoringEndpoint toCreate);
    void onGet(Long id);
    void onUpdate(MonitoringEndpoint toUpdate, Long id);
    void onRemove(Long id);
}