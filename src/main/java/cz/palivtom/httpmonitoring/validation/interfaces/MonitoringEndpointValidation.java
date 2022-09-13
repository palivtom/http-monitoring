package cz.palivtom.httpmonitoring.validation.interfaces;

import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;

/**
 * Validating class. If anything fails, throws the {@link ApiRuntimeRuntimeException}<br>
 *
 * Methods are matching endpoints (if any validation is needed)
 */
public interface MonitoringEndpointValidation {
    void onCreate(MonitoringEndpoint toCreate);
    void onGet(Long id);
    void onUpdate(MonitoringEndpoint toUpdate, Long id);
    void onRemove(Long id);
}