package cz.palivtom.httpmonitoring.mapper;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointDto;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointPageableDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper
public interface MonitoringEndpointMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "checkedAt", ignore = true)
    MonitoringEndpoint map(MonitoringEndpointDto monitoringEndpointDto);

    @Mapping(source = "user.id", target = "authorId")
    @Mapping(target = "responses", ignore = true)
    MonitoringEndpointDto map(MonitoringEndpoint monitoringEndpoint);

    @Mapping(source = "totalPages", target = "pagesOverAll")
    @Mapping(source = "pageable.pageNumber", target = "page")
    @Mapping(source = "pageable.pageSize", target = "pageSize")
    @Mapping(source = "content", target = "monitoringEndpoints")
    MonitoringEndpointPageableDto map(Page<MonitoringEndpoint> monitoringEndpointPage);
}