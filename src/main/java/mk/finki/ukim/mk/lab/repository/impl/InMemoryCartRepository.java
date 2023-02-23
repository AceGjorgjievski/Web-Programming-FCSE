package mk.finki.ukim.mk.lab.repository.impl;


import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Cart;
import mk.finki.ukim.mk.lab.model.enums.CartStatus;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Optional;

@Repository
public class InMemoryCartRepository {

    public Optional<Cart> findById(Long id) {
        return DataHolder.carts.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Cart save(Cart cart) {
        DataHolder.carts
                .removeIf(i ->i.getUser().getUsername().equals(cart.getUser().getUsername()));
        DataHolder.carts.add(cart);

        return cart;
    }

    public Optional<Cart> findByUsernameAndStatus(String username, CartStatus status) {
        return DataHolder.carts
                .stream()
                .filter(i -> i.getUser().getUsername().equals(username) &&
                        i.getCartStatus().equals(status))
                .findFirst();
    }

}
