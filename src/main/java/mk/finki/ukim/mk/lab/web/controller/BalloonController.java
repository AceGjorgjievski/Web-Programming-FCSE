package mk.finki.ukim.mk.lab.web.controller;

import lombok.EqualsAndHashCode;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Cart;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.CartService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/balloons")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class BalloonController {

    private final ManufacturerService manufacturerService;
    private final BalloonService balloonService;
    private final CartService cartService;

    public BalloonController(ManufacturerService manufacturerService,
                             BalloonService balloonService,
                             CartService cartService) {
        this.manufacturerService = manufacturerService;
        this.balloonService = balloonService;
        this.cartService = cartService;
    }
    //add bez, edit so datum

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMsg", error);
        }
        List<Balloon> balloonList = this.balloonService.listAll();

        //zar ova balloons mi beshe null koga od /add mapirav direktno na listballoons.html
        //ne se zachuvuva bidejkji ne e vo sesija? i mora preku this.balloonService.listAll(); da se zemat
        model.addAttribute("balloons", balloonList);
        model.addAttribute("bodyContent","listBalloons");


        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();

        model.addAttribute("mans", manufacturers);
        model.addAttribute("bodyContent","add-balloon");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (this.balloonService.findById(id).isPresent()) {
            Balloon b = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Balloon> balloons = this.balloonService.listAll();
            model.addAttribute("mans", manufacturers);
            model.addAttribute("balloon", b);
            model.addAttribute("balloons", balloons);
            model.addAttribute("bodyContent","add-balloon");
            return "master-template";
        }

        return "redirect:/balloons?error=BalloonNotFound";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer) {

        if (id != null) {
            this.balloonService.edit(id, name, description, manufacturer);
        } else {
            this.balloonService.save(name, description, manufacturer);
        }


        //da ne treba da se povika ova return kon /balloons t.e. eden redirect bidejkji kje treba da gi pokazhe
        // kaj model
        return "redirect:/balloons";
    }

    @PostMapping("/addToCart/{id}")
    public String addBalloonToCart(@PathVariable Long id, HttpServletRequest request) {

        try {
            String user = request.getRemoteUser();
            Cart c = this.cartService.addBalloonToCart(user, id);
            return "redirect:/cart";
        } catch (RuntimeException e) {
            return "redirect:/cart?error=" + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }
}