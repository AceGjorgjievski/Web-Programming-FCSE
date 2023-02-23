package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mk.finki.ukim.mk.lab.model.enums.CartStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Balloon> balloons;

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    @ManyToMany
    private List<Order> orders;

    public Cart() {
        this.balloons = new ArrayList<>();
    }

    public Cart(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.balloons = new ArrayList<>();
        this.cartStatus = CartStatus.CREATED;
        this.dateCreated = LocalDateTime.now();
//        System.out.println("Cart - date creation: " + dateCreated);
    }
}
