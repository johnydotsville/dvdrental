package johny.dotsville.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store")
@Getter @Setter
public class Store extends AbstractEntity {
    @Id
    @Column(name = "store_id")
    @SequenceGenerator(name = "store_id_gen", sequenceName = "store_store_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "store_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_staff_id")
    private Staff manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public String toString() {
        return address.toString();
    }
}
