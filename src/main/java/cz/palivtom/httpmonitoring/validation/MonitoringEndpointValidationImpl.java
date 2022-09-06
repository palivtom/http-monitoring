package cz.palivtom.httpmonitoring.validation;

import cz.palivtom.httpmonitoring.repository.MonitoringEndpointRepository;
import cz.palivtom.httpmonitoring.validation.interfaces.MonitoringEndpointValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonitoringEndpointValidationImpl implements MonitoringEndpointValidation {

    private final MonitoringEndpointRepository monitoringEndpointRepository;

    //todo
}