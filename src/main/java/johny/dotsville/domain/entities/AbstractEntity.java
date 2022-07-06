package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @ToString
public abstract class AbstractEntity {
    @Column(name = "last_update", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.Generated(
            value = org.hibernate.annotations.GenerationTime.ALWAYS)
    protected LocalDateTime lastUpdate;

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (this.getClass() != object.getClass()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
