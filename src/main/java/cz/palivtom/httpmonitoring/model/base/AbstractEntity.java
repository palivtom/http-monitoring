package cz.palivtom.httpmonitoring.model.base;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<U extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private U id = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity<?> that = (AbstractEntity<?>) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}