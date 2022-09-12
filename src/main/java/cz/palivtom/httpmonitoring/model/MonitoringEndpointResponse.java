package cz.palivtom.httpmonitoring.model;

import cz.palivtom.httpmonitoring.model.base.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "monitoring_endpoint_response")
@Table(indexes = {
        @Index(name = "idx_monitoringEndpointResponse_monitoringEndpointId_createdAt", columnList = "monitoring_endpoint_id, created_at")
})
public class MonitoringEndpointResponse extends AbstractEntity<Long> {
    public MonitoringEndpointResponse(MonitoringEndpoint monitoringEndpoint, String payload, int httpStatusCode) {
        this.monitoringEndpoint = monitoringEndpoint;
        this.payload = payload;
        this.httpStatusCode = httpStatusCode;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "monitoring_endpoint_id")
    private MonitoringEndpoint monitoringEndpoint;

    @Column(name = "payload")
    @Lob
    private String payload;

    @Column(name = "http_status_code", nullable = false)
    private Integer httpStatusCode;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitoringEndpointResponse that = (MonitoringEndpointResponse) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}