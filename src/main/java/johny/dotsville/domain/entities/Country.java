package johny.dotsville.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "country")
@Getter @Setter
public class Country extends AbstractEntity {
    @Id
    @Column(name = "country_id")
    @SequenceGenerator(name = "country_id_gen", sequenceName = "country_country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_gen")
    private long id;

    @Column(name = "country")
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object))
            return false;
        Country country = (Country) object;
        return Objects.equals(id, country.id);
    }
}
