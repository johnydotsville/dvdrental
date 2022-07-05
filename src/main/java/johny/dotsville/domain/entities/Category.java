package johny.dotsville.domain.entities;

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
@Table(name = "category")
@Getter @Setter
public class Category extends AbstractEntity {
    @Id
    @Column(name = "category_id")
    @SequenceGenerator(name = "category_id_gen", sequenceName = "category_category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_gen")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<FilmCategory> filmCategories = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) return false;
        Category category = (Category) object;
        return Objects.equals(this.id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
