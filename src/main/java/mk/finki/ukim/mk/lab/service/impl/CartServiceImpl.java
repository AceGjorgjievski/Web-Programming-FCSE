package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Cart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.CartStatus;
import mk.finki.ukim.mk.lab.model.exeptions.BalloonAlreadyInCartException;
import mk.finki.ukim.mk.lab.model.exeptions.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.exeptions.CartNotFoundException;
import mk.finki.ukim.mk.lab.model.exeptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.CartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BalloonRepository balloonRepository;
    private final BalloonService balloonService;

    public CartServiceImpl(CartRepository cartRepository,
                           UserRepository userRepository,
                           BalloonRepository balloonRepository,
                           BalloonService balloonService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.balloonRepository = balloonRepository;
        this.balloonService = balloonService;
    }


    @Override
    public List<Balloon> listAllBalloonsInShoppingCart(Long cartId) {
        if(!this.cartRepository.findById(cartId).isPresent())
            throw new CartNotFoundException(cartId);

        return this.cartRepository.findById(cartId).get().getBalloons();
    }

    @Override
    public Cart getActiveCart(String username) {
        User u = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

//         Cart local = Optional.of(this.cartRepository.findBy(u, CartStatus.CREATED));

        return this.cartRepository.findByUserAndCartStatus(u, CartStatus.CREATED)
                .orElseGet(() -> {
                    Cart cart = new Cart(u);
                    return this.cartRepository.save(cart);
                });
    }

    @Override
    public Cart addBalloonToCart(String username, Long balloonId) {
        Cart cart = this.getActiveCart(username);
        Balloon b = this.balloonService
                .findById(balloonId)
                .orElseThrow(() -> new BalloonNotFoundException(balloonId));

        if(cart.getBalloons()
                .stream()
                .filter(i -> i.getId().equals(balloonId))
                .collect(Collectors.toList()).size() > 0) {
            throw new BalloonAlreadyInCartException(balloonId, username);
        }

        cart.getBalloons().add(b);


        return this.cartRepository.save(cart);
    }

    @Override
    public void removeBalloon(Cart cart, Long id) {

        List<Balloon> balloons = cart.getBalloons();

        balloons = balloons
                .stream()
                .filter(i -> i.getId().intValue() != id.intValue())
                .collect(Collectors.toList());
        cart.setBalloons(balloons);

        this.cartRepository.save(cart);
    }
}
