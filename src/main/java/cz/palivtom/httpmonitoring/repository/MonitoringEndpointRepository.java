package cz.palivtom.httpmonitoring.repository;

import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MonitoringEndpointRepository extends JpaRepository<MonitoringEndpoint, Long> {
    Page<MonitoringEndpoint> findAllByUser_Email(String email, Pageable pageable);
    Page<MonitoringEndpoint> findAllByUser_EmailAndDeletedAt(String email, LocalDateTime deletedAt, Pageable pageable);
}