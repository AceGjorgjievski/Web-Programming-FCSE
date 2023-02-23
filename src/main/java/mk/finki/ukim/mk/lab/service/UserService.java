package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String name,
                  String surname,
                  String username,
                  String password,
                  String passwordRepeat,
                  Role role);

}
