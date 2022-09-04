package cz.palivtom.httpmonitoring.service;

import cz.palivtom.httpmonitoring.repository.MonitoringEndpointRepository;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointService;
import cz.palivtom.httpmonitoring.validation.interfaces.MonitoringEndpointValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MonitoringEndpointServiceImpl implements MonitoringEndpointService {

    private final MonitoringEndpointValidation monitoringEndpointValidation;
    private final MonitoringEndpointRepository monitoringEndpointRepository;

    //todo

}