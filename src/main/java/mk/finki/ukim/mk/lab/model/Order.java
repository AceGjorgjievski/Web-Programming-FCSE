package mk.finki.ukim.mk.lab.model;


import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    String balloonColor;

    String balloonSize;

    public Order(String balloonColor, String balloonSize, Long orderId) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.orderId = orderId;
    }

    public Order() {

    }
}
