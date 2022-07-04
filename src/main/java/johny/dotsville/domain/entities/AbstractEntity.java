package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @ToString @EqualsAndHashCode
public abstract class AbstractEntity {
    @Column(name = "last_update", insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.Generated(
            value = org.hibernate.annotations.GenerationTime.ALWAYS)
    protected LocalDateTime lastUpdate;
}
