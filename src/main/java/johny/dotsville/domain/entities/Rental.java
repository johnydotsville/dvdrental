package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rental")
@Getter @Setter
public class Rental extends AbstractEntity {
    @Id
    @Column(name = "rental_id")
    @SequenceGenerator(name = "rental_id_gen", sequenceName = "rental_rental_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "rental_id_gen", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object))
            return false;
        Rental rental = (Rental) object;
        return Objects.equals(id, rental.id);
    }
}
