package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Cart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.CartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;

    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCreateOrderPage() {
        return "createOrder";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloonFromCart(@PathVariable Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        Cart cart = this.cartService.getActiveCart(user.getUsername());

        this.cartService.removeBalloon(cart, id);
        return "userOrders";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("localDateTime")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {

        System.out.println();
        return "";
    }
}
