package cz.palivtom.httpmonitoring.model;

import cz.palivtom.httpmonitoring.model.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends AbstractEntity<Long> {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "access_token", length = 36, nullable = false, unique = true)
    private String accessToken = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "user")
    private List<MonitoringEndpoint> monitoringEndpoints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}