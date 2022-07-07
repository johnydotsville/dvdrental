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
import lombok.Setter;

import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter @Setter
public class Film extends AbstractEntity {
    @Id
    @Column(name = "film_id")
    @SequenceGenerator(name = "film_id_gen", sequenceName = "film_film_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_gen")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Year releaseYear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Column(name = "length")
    private int length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String[] specialFeatures;

    @Column(name = "fulltext")
    private String fulltext;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
    private Set<FilmCategory> filmCategory = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
    private Set<FilmActor> filmActor = new HashSet<>();

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object))
            return false;
        Film film = (Film) object;
        return Objects.equals(id, film.id);
    }
}
