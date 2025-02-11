//این خط مشخص می‌کند که کلاس Payments در پکیج ir.reyhaneh.hotelreservation.model قرار دارد.
package ir.reyhaneh.hotelreservation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

//هدف: نگهداری اطلعات مربوط به پرداختها.
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private Date paymentDate;

    private String paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Bookings bookings;

    public Payments(Long id, Long amount, Date paymentDate, String paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public Payments() {
    }
}