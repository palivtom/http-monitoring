package cz.palivtom.httpmonitoring.facade;

import cz.palivtom.httpmonitoring.facade.interfaces.MonitoringEndpointFacade;
import cz.palivtom.httpmonitoring.mapper.MonitoringEndpointMapper;
import cz.palivtom.httpmonitoring.mapper.MonitoringEndpointResponseMapper;
import cz.palivtom.httpmonitoring.mapper.PageableMapper;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointDto;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointPageableDto;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointResponseService;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonitoringEndpointFacadeImpl implements MonitoringEndpointFacade {

    private final MonitoringEndpointService monitoringEndpointService;
    private final MonitoringEndpointResponseService monitoringEndpointResponseService;
    private final MonitoringEndpointMapper monitoringEndpointMapper;
    private final MonitoringEndpointResponseMapper monitoringEndpointResponseMapper;
    private final PageableMapper pageableMapper;


    @Override
    public MonitoringEndpointDto create(MonitoringEndpointDto monitoringEndpointDto) {
        return monitoringEndpointMapper.map(
                monitoringEndpointService.create(
                        monitoringEndpointMapper.map(monitoringEndpointDto)
                )
        );
    }

    @Override
    public MonitoringEndpointDto delete(Long monitoringEndpointId) {
        return monitoringEndpointMapper.map(
                monitoringEndpointService.delete(monitoringEndpointId)
        );
    }

    @Override
    public MonitoringEndpointDto update(MonitoringEndpointDto monitoringEndpointDto, Long monitoringEndpointId) {
        return monitoringEndpointMapper.map(
                monitoringEndpointService.update(
                        monitoringEndpointMapper.map(monitoringEndpointDto), monitoringEndpointId
                )
        );
    }

    @Override
    public MonitoringEndpointDto get(Long monitoringEndpointId) {
        MonitoringEndpointDto result = monitoringEndpointMapper.map(
                monitoringEndpointService.get(monitoringEndpointId)
        );

        if (result != null) {
            result.responses(
                    monitoringEndpointResponseMapper.map(
                            monitoringEndpointResponseService.getTop10(result.getId())
                    )
            );
        }

        return result;
    }

    @Override
    public MonitoringEndpointPageableDto getAll(Integer pageSize, Integer page, String filter, String order, Boolean validOnly) {
        Pageable pageable = pageableMapper.map(page, pageSize, order, filter);
        MonitoringEndpointPageableDto result = monitoringEndpointMapper.map(
                monitoringEndpointService.getAll(pageable, validOnly)
        );

        if (result != null && result.getMonitoringEndpoints() != null) {
            result.getMonitoringEndpoints().forEach(endpoint ->
                    endpoint.responses(monitoringEndpointResponseMapper.map(
                            monitoringEndpointResponseService.getTop10(endpoint.getId())
                    ))
            );
        }

        return result;
    }
}