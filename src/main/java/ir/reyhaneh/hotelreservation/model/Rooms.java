package ir.reyhaneh.hotelreservation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

//هدف: نگهداری اطلعات مربوط به اتاقهای هتل.

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Rooms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String type;

    private String status;

    private Long price;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Bookings> bookings;


    public Rooms() {
    }

    public Rooms(Long id, String type, String status, Long price) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.price = price;
    }
}
