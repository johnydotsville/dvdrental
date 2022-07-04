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
@Table(name = "language")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Language extends AbstractEntity {
    @Id
    @Column(name = "language_id")
    @SequenceGenerator(name = "language_id_gen", sequenceName = "language_language_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_gen")
    private Long id;
    @Column(name = "name")
    private String name;
}
