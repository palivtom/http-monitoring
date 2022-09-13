package cz.palivtom.httpmonitoring.service.interfaces;

import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;

public interface MonitoringEndpointService {

    /**
     * Creates the {@link MonitoringEndpoint} by the given object and authorizes it for the currently logged in {@link User}<br>
     *
     * {@link ApiRuntimeRuntimeException} if validation fails
     *
     * @param toCreate the data source object
     * @return saved and registered entity to scheduled executor pool
     */
    MonitoringEndpoint create(MonitoringEndpoint toCreate);

    /**
     * Getting the page of {@link MonitoringEndpoint} elements for the currently logged in {@link User}
     *
     * @param pageable sorting, filtering, paging
     * @param validOnly if deleted elements are allowed
     * @return filtered page for current user in {@link SecurityContext}
     */
    Page<MonitoringEndpoint> getAll(Pageable pageable, boolean validOnly);

    /**
     * Getting single element if currently logged in {@link User} is authorized to<br>
     *
     * {@link ApiRuntimeRuntimeException} if validation fails
     *
     * @param id of entity to get
     * @return the {@link MonitoringEndpoint} entity
     */
    MonitoringEndpoint get(Long id);

    /**
     * Updates the {@link MonitoringEndpoint} by the given object if currently logged in {@link User} is authorized to<br>
     *
     * {@link ApiRuntimeRuntimeException} if validation fails
     *
     * @param toUpdate the data source object
     * @param id of entity to update
     * @return updated re-registered entity in scheduled executor pool
     */
    MonitoringEndpoint update(MonitoringEndpoint toUpdate, Long id);

    /**
     * Deletes the {@link MonitoringEndpoint} by the given id if currently logged in {@link User} is authorized to<br>
     * Delete means applying the current datetime to attribute {@link MonitoringEndpoint#deletedAt}<br>
     *
     * {@link ApiRuntimeRuntimeException} if validation fails
     *
     * @param id of entity to delete
     * @return deleted entity
     */
    MonitoringEndpoint delete(Long id);
}