package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Cart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exeptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCartPage(@RequestParam(required = false) String error,
                              HttpServletRequest request,
                              Model model) {
        if(error!= null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        /**
         * informacijata za toa koj korisnik e najaven
         * se chuva vo 'remoteUser' header-ot koga
         * koristime bazichna avtentikacija
         */
        String username = request.getRemoteUser();
        Cart cart = null;

        try {
            cart = this.cartService.getActiveCart(username);
            model.addAttribute("balloons",this.cartService.listAllBalloonsInShoppingCart(cart.getId()));
            model.addAttribute("bodyContent","userOrders");
            request.getSession().setAttribute("user",username);

            return "master-template";
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return "redirect:/cart?error="+e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloonFromCart(@PathVariable Long id, HttpServletRequest request) {

        String user = request.getRemoteUser();
        Cart cart = this.cartService.getActiveCart(user);


        this.cartService.removeBalloon(cart, id);
        return "redirect:/cart";
    }
}
