package mk.finki.ukim.mk.lab;


import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;
import mk.finki.ukim.mk.lab.model.exeptions.InvalidUserRegisterException;
import mk.finki.ukim.mk.lab.model.exeptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.exeptions.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.impl.AuthServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    //simulirani povici
    @Mock
    private UserRepository userRepository;

    private AuthServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("name","surname","username","password", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);

        this.service = Mockito.spy(new AuthServiceImpl(this.userRepository));
    }

    @Test
    public void testSuccessRegister() {
        User user = this.service.register("name",
                "surname",
                "username",
                "password",
                "password",
                Role.ROLE_USER);

        Mockito.verify(this.service).register("name",
                "surname",
                "username",
                "password",
                "password",
                Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);

        Assert.assertEquals("name do not match","name",user.getName());
        Assert.assertEquals("surname do not match","surname",user.getSurname());
        Assert.assertEquals("username do not match","username",user.getUsername());
        Assert.assertEquals("password do not match","password",user.getPassword());
        Assert.assertEquals("role do not match",Role.ROLE_USER,user.getRole());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidUserRegisterException expected",
                InvalidUserRegisterException.class,
                () -> this.service.register(null, "surname", "username", "password", "password", Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "surname", "username", "password", "password", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidUserRegisterException expected",
                InvalidUserRegisterException.class,
                () -> this.service.register(username, "surname", "username", "password", "password", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "surname", "username", "password", "password", Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidUserRegisterException expected",
                InvalidUserRegisterException.class,
                () -> this.service.register(username, password, "username", "password", "password", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "username", "password", "password", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidUserRegisterException expected",
                InvalidUserRegisterException.class,
                () -> this.service.register(username, password, "username", "password", "password", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "username", "password", "password", Role.ROLE_USER);
    }

    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        User user = new User("name","surname","username","password", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "surname", "username", "password", "password", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "surname", "username", "password", "password", Role.ROLE_USER);
    }
}
