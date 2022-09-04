package cz.palivtom.httpmonitoring.validation;

import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointService;
import cz.palivtom.httpmonitoring.validation.interfaces.MonitoringEndpointValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonitoringEndpointValidationImpl implements MonitoringEndpointValidation {

    private final MonitoringEndpointService monitoringEndpointService;

    //todo
}