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
import lombok.Setter;
import lombok.Getter;

import java.util.Objects;

@Entity
@Table(name = "address")
@Getter @Setter
public class Address extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "address_id_gen", sequenceName = "address_address_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "address_id_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Override
    public String toString() {
        return String.format("%s, %s (%s), %s (%s)",
                city.toString(),
                address,
                address2,
                district,
                phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object))
            return false;
        Address address = (Address) object;
        return Objects.equals(id, address.id);
    }
}
