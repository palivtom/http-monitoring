package cz.palivtom.httpmonitoring.repository;

import cz.palivtom.httpmonitoring.model.MonitoringEndpointResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringEndpointResponseRepository extends JpaRepository<MonitoringEndpointResponse, Long> {
    List<MonitoringEndpointResponse> findTop10ByMonitoringEndpoint_IdOrderByCreatedAtDesc(Long id);
}