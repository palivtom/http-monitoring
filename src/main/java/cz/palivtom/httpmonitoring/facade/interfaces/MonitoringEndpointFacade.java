package cz.palivtom.httpmonitoring.facade.interfaces;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointDto;
import cz.palivtom.httpmonitoring.model.dto.MonitoringEndpointPageableDto;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointResponseService;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointService;

/**
 * This class is wrapping the dto mapping and data aggregation.
 */
public interface MonitoringEndpointFacade {

    /**
     * Dto mapping and calling the {@link MonitoringEndpointService#create} method
     *
     * @param monitoringEndpointDto the data source
     * @return created entity mapped back to the dto object
     */
    MonitoringEndpointDto create(MonitoringEndpointDto monitoringEndpointDto);

    /**
     * Dto mapping and calling the {@link MonitoringEndpointService#delete} method
     *
     * @param monitoringEndpointId id of entity to delete
     * @return deleted entity mapped back to the dto object
     */
    MonitoringEndpointDto delete(Long monitoringEndpointId);

    /**
     * Dto mapping and calling the {@link MonitoringEndpointService#update} method
     *
     * @param monitoringEndpointDto the data source
     * @param monitoringEndpointId id of entity to update
     * @return updated entity mapped back to the dto object
     */
    MonitoringEndpointDto update(MonitoringEndpointDto monitoringEndpointDto, Long monitoringEndpointId);

    /**
     * Dto mapping and calling the {@link MonitoringEndpointService#get} with aggregation data call to {@link MonitoringEndpointResponseService#getTop10}
     *
     * @param monitoringEndpointId id of entity to get
     * @return founded {@link MonitoringEndpoint} with the list of top ten {@link MonitoringEndpointResponse}
     */
    MonitoringEndpointDto get(Long monitoringEndpointId);

    /**
     * Dto mapping and calling the {@link MonitoringEndpointService#getAll} with aggregation data call to {@link MonitoringEndpointResponseService#getTop10}
     *
     * @param pageSize element count per single page
     * @param page the current page
     * @param filter name of JSON attribute
     * @param order DESC or ASC
     * @param validOnly return also the deleted elements
     * @return page of founded {@link MonitoringEndpoint} with the list of top ten {@link MonitoringEndpointResponse}
     */
    MonitoringEndpointPageableDto getAll(Integer pageSize, Integer page, String filter, String order, Boolean validOnly);
}