package johny.dotsville.domain.entities;

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

// Связь фильмов с магазинами
// Какие фильмы в каких магазинах есть
// TODO: подумать, стоит ли делать в фильмах список магазинов, а в магазине список фильмов
@Entity
@Table(name = "inventory")
@Setter @Getter
public class Inventory extends AbstractEntity {
    @Id
    @Column(name = "inventory_id")
    @SequenceGenerator(name = "inventory_id_gen", sequenceName = "inventory_inventory_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_id_gen")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object))
            return false;
        Inventory inventory = (Inventory) object;
        return Objects.equals(id, inventory.id);
    }
}
