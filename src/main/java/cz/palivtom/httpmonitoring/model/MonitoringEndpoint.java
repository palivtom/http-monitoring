package cz.palivtom.httpmonitoring.model;

import cz.palivtom.httpmonitoring.model.base.AbstractValidityEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "monitoring_endpoint")
@Table(indexes = {
        @Index(name = "idx_monitoringEndpoint_userId_createdAt", columnList = "user_id, created_at")
})
public class MonitoringEndpoint extends AbstractValidityEntity<Long> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", length = 2048, nullable = false)
    private String url;

    @Column(name = "sec_interval", nullable = false)
    private Integer interval;

    @Enumerated(EnumType.STRING)
    @Column(name = "http_method", nullable = false)
    private HttpMethod httpMethod = HttpMethod.GET;

    @OneToMany(mappedBy = "monitoringEndpoint")
    private List<MonitoringEndpointResponse> responses;

    @Column(name = "checked_at", nullable = true)
    private LocalDateTime checkedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitoringEndpoint that = (MonitoringEndpoint) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}