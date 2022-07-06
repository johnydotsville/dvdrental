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
@Table(name = "staff")
@Getter  @Setter
public class Staff extends AbstractEntity {
    @Id
    @Column(name = "staff_id")
    @SequenceGenerator(name = "staff_id_gen", sequenceName = "staff_staff_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "staff_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "active")
    private boolean active;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    // TODO: Пока можно жить и без этого
    private byte[] picture;
}
