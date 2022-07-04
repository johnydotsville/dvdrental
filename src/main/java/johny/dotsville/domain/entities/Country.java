package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "country")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Country extends AbstractEntity {
    @Id
    @Column(name = "country_id")
    @SequenceGenerator(name = "country_id_gen", sequenceName = "country_country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_gen")
    private Long id;
    @Column(name = "country")
    private String country;
}
