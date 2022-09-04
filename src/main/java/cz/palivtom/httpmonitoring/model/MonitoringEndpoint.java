package cz.palivtom.httpmonitoring.model;

import cz.palivtom.httpmonitoring.model.base.AbstractValidityEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity(name = "monitoring_endpoints")
public class MonitoringEndpoint extends AbstractValidityEntity<Long> {

    //todo

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