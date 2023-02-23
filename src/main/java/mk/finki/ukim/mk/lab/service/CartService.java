package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Cart;

import java.util.List;

public interface CartService {
    List<Balloon> listAllBalloonsInShoppingCart(Long cartId);
    Cart getActiveCart(String username);
    Cart addBalloonToCart(String username, Long id);
    void removeBalloon(Cart cart, Long id);
}
