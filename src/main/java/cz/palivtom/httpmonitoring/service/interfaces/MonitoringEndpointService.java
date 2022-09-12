package cz.palivtom.httpmonitoring.service.interfaces;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonitoringEndpointService {
    MonitoringEndpoint create(MonitoringEndpoint toCreate);
    Page<MonitoringEndpoint> getAll(Pageable pageable, boolean validOnly);
    MonitoringEndpoint get(Long id);
    MonitoringEndpoint update(MonitoringEndpoint toUpdate, Long id);
    MonitoringEndpoint delete(Long id);
}