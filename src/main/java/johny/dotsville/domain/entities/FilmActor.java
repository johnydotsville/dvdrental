package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "film_actor")
@Getter @Setter
public class FilmActor extends AbstractEntity {
    @EmbeddedId
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id", insertable = false, updatable = false)
    private Actor actor;

    @Setter @Getter
    public static class Id implements Serializable {
        @Column(name = "actor_id")
        private long actorId;
        @Column(name = "film_id")
        private long filmId;

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || object.getClass() != this.getClass()) return false;
            Id id = (Id) object;
            return Objects.equals(actorId, id.actorId)
                && Objects.equals(filmId, id.filmId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), actorId, filmId);
        }
    }
}
