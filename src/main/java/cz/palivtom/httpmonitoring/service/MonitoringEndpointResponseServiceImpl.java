package cz.palivtom.httpmonitoring.service;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;
import cz.palivtom.httpmonitoring.repository.MonitoringEndpointRepository;
import cz.palivtom.httpmonitoring.repository.MonitoringEndpointResponseRepository;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MonitoringEndpointResponseServiceImpl implements MonitoringEndpointResponseService {

    private final MonitoringEndpointResponseRepository monitoringEndpointResponseRepository;
    private final MonitoringEndpointRepository monitoringEndpointRepository;

    @Override
    public List<MonitoringEndpointResponse> getTop10(Long monitoringEndpointId) {
        return monitoringEndpointResponseRepository.findTop10ByMonitoringEndpoint_IdOrderByCreatedAtDesc(monitoringEndpointId);
    }

    @Override
    public void saveResponse(MonitoringEndpointResponse response) {
        MonitoringEndpoint monitoringEndpoint = response.getMonitoringEndpoint();
        monitoringEndpoint.setCheckedAt(response.getCreatedAt());
        monitoringEndpointRepository.save(monitoringEndpoint);
        monitoringEndpointResponseRepository.save(response);
    }
}