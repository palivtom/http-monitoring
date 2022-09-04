package cz.palivtom.httpmonitoring.repository;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringEndpointRepository extends JpaRepository<MonitoringEndpoint, Long> {
}