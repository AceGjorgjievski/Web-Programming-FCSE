package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;
import mk.finki.ukim.mk.lab.model.exeptions.*;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryUserRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) throw new InvalidArgumentException(username, password);

        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }



    @Override
    public User register(String name,
                         String surname,
                         String username,
                         String password,
                         String passwordRepeat,
                         Role role) {
        if (name == null || name.isEmpty() ||
                surname == null || surname.isEmpty() ||
                username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                passwordRepeat == null || passwordRepeat.isEmpty()) throw new InvalidUserRegisterException();

        if (!password.equals(passwordRepeat)) throw new PasswordsDoNotMatchException();


          //nema da se dozvoi brishenje na korisnik vo baza i ako
          //nekoj drug dojde da se registrira i ovoj shto postoi vo baza
          //ne treba da se izbrishe
          //kako shto beshe vo 'saveOrUpdate()' via - InMemoryUserRepository.class

        if(this.userRepository.findByUsername(username).isPresent() ||
        !this.userRepository.findByUsername(username).isEmpty()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User newUser = new User(name, surname, username, password, role);

        return this.userRepository.save(newUser);
    }
}