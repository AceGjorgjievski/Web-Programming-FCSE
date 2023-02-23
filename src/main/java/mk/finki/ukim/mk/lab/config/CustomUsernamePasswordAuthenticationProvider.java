package mk.finki.ukim.mk.lab.config;

import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider
{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(UserService userService,
                                                        PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * ovoj method se povikuva vo onoj moment koga se pravi 'POST' baranje do '/login'
     * so toa shto korisnikot vnesuva username i password.
     *
     * (se povikuva koga se klika na 'sign in')
     *
     * Informaciite koi shto korisnikot gi vnel (username & password) se naogjaat vo parametarot
     * 'authentication'
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if("".equals(username) || "".equals(password)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        UserDetails userDetails = this.userService.loadUserByUsername(username);

        if(!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
