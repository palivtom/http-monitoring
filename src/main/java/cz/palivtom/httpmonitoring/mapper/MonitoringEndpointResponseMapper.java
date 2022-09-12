package cz.palivtom.httpmonitoring.mapper;

import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MonitoringEndpointResponseMapper {

    @Mapping(source = "httpStatusCode", target = "httpCode")
    List<MonitoringEndpointResponseDto> map(List<MonitoringEndpointResponse> monitoringEndpointResponses);

    @Mapping(source = "httpStatusCode", target = "httpCode")
    MonitoringEndpointResponseDto map(MonitoringEndpointResponse monitoringEndpointResponse);
}