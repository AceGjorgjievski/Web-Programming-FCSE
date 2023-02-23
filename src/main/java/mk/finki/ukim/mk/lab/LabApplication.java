package mk.finki.ukim.mk.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ServletComponentScan
public class LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }


    /**
     *
     * @Bean - bidejkji passwordEncoder ne e nasha klasa i ne mozheme
     * da postavime anotaicja na taa klasa, nas ni' e potreben factory
     * method t.e. metod anotiran so @Bean
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        //kolku e pogolema ovaa vrednost tolku hash-iranata vrednost kje bide na
        //ponavisoko nivo, a kolku povekje se hash-ira, celiot algoritam se komplicira
        //i ako se vnese golema vrednost za 'strength', togash pri login kje se cheka podolgo vreme
        //zatoa shto e potrebno nekolku iteracii za da algoritmot napravi hashiranje na lozinkata
        //strength=10 e nekoja optimalna vrednost
        return new BCryptPasswordEncoder(10);
    }
}
