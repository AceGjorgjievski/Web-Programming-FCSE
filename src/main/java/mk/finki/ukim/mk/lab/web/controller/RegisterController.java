package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.enums.Role;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService,
                              UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(HttpServletRequest request, Model model) {
        // preku request param
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String repeatPassword = request.getParameter("password-repeat");
            String userRole = request.getParameter("role");
            Role role = Role.valueOf(userRole);

            this.userService.register(name,
                    surname,
                    username,
                    password,
                    repeatPassword,
                    role);

//            this.authService.register(name,
//                    surname,
//                    username,
//                    password,
//                    repeatPassword,
//                    role);

            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("hasRegisterError", true);
            model.addAttribute("registerMessageError", e.getMessage());
            return "register";
        }
    }


}
