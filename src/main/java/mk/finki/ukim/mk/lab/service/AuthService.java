package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;

public interface AuthService {
    User login(String username, String password);
    User register(String name,
                  String surname,
                  String username,
                  String password,
                  String passwordRepeat,
                  Role role);
}
