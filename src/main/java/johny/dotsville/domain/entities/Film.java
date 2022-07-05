package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter @Setter @NoArgsConstructor @ToString
public class Film extends AbstractEntity {
    @Id
    @Column(name = "film_id")
    @SequenceGenerator(name = "film_id_gen", sequenceName = "film_film_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_gen")
    private Long id;
    @Column(name = "title")
    private String title;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
    private Set<FilmCategory> filmCategories = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) return false;
        Film film = (Film) object;
        return Objects.equals(this.id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title);
    }
}
