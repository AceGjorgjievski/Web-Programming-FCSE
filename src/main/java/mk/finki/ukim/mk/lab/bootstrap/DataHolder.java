package mk.finki.ukim.mk.lab.bootstrap;


import lombok.Getter;
import mk.finki.ukim.mk.lab.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Balloon> balloonList = new ArrayList<>(10);
    public static List<Order> orders = new ArrayList<>(10);
    public static List<Manufacturer> manufacturers = new ArrayList<>(5);
    public static List<Cart> carts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();


    @PostConstruct
    public void init() {

//        Manufacturer m1 = new Manufacturer("NikeOriginal", "USA","Street 1");
//        manufacturers.add(m1);
//        Manufacturer m2 = new Manufacturer("AdidasOriginal", "Korea","Street 2");
//        manufacturers.add(m2);
//
//
//        balloonList.add(new Balloon("B1", "B1-Description",m1));
//        balloonList.add(new Balloon("B2", "B2-Description",m2));
//
//        users.add(new User("ace","gjorgjievski","ag","ag"));


    }
}
