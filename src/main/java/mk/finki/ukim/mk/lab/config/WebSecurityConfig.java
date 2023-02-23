package mk.finki.ukim.mk.lab.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider
    ) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//da ne se ovozmozhuvaat povici kon nashata aplikacija od 3rd part aplikaii (cross-site-request-forgery)
                .authorizeRequests() //koi stranici treba da ni bidat dostapni za koi korisnici (za korisnici koi shto ne ja najaveni)
                    .antMatchers("/","/home","/assets/**","/register","/balloons").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN") //samo uloga administrator
                    .anyRequest().authenticated() //site ostanati requesti treba korisnikot da bide avtentificiran (najaveni)
                .and()
                .formLogin()//konfiguracija na login formata
                    .loginPage("/login").permitAll() //sekoj mozhe da ja pristapi
                    .failureUrl("/login?error=BadCredentials") //exception pri najava na korisnikot
                    .defaultSuccessUrl("/balloons",true) //po uspeshna najava, korisnikot treba da bide najaven na 'defaultSuccessUrl'
                .and()
                .logout()//konfiguracija na logout formata
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)//bidejkji e statefull najavata, ja invalidirame
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login") //pri uspeshna odjava, korisnikot da bide prepraten kon "/login" (vo ovoj sluchaj)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //provajder koj shto kje pravime avtentifikacija
//        auth.inMemoryAuthentication()
//                .withUser("ag")
//                .password(this.passwordEncoder.encode("ag")) //da ne e plain text lozinka
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("admin")
//                .password(this.passwordEncoder.encode("admin"))
//                .authorities("ROLE_ADMIN");

        auth.authenticationProvider(this.customUsernamePasswordAuthenticationProvider);
    }
}
