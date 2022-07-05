package johny.dotsville.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "city")
@Getter @Setter
public class City extends AbstractEntity {
    @Id
    @Column(name = "city_id")
    @SequenceGenerator(name = "city_id_gen", sequenceName = "city_city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_gen")
    private Long id;
    @Column(name = "city")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) return false;
        City city = (City) object;
        return Objects.equals(id, city.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
