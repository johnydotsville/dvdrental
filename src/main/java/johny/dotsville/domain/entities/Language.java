package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "language")
@Getter @Setter
public class Language extends AbstractEntity {
    @Id
    @Column(name = "language_id")
    @SequenceGenerator(name = "language_id_gen", sequenceName = "language_language_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_gen")
    private Long id;
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) return false;
        Language lang = (Language) object;
        return Objects.equals(id, lang.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
