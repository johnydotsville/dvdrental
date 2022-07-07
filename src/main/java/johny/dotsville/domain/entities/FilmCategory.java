package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "film_category")
@Getter @Setter
public class FilmCategory extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @EmbeddedId
    private Id id = new Id();

    private FilmCategory() { }

    public FilmCategory(Film film, Category category) {
        this.film = film;
        this.category = category;

        this.id.categoryId = category.getId();
        this.id.filmId = film.getId();

        film.getFilmCategory().add(this);
        category.getFilmCategory().add(this);
    }

    @Embeddable
    @Getter @Setter
    public static class Id implements Serializable {
        @Column(name = "film_id")
        private Long filmId;

        @Column(name = "category_id")
        private Long categoryId;

        @Override
        public boolean equals(Object object) {
            if (!super.equals(object))
                return false;
            Id id = (Id) object;
            return Objects.equals(filmId, id.filmId)
                    && Objects.equals(categoryId, id.categoryId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(filmId, categoryId);
        }
    }
}
