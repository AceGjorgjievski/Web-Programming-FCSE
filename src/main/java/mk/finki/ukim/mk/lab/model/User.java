package mk.finki.ukim.mk.lab.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.finki.ukim.mk.lab.model.enums.Role;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name="shop_user")
public class User implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;
    private Long id;

//    @Getter
//    @Setter
    @Column(name="USERNAMEE")
    private String username;
//    @Getter
//    @Setter
    private String password;
//    @Getter
//    @Setter
    @Column(name = "NAME")
    private String name;
//    @Getter
//    @Setter
    private String surname;

//    private boolean isAccountNonExpired = true;
//    private boolean isAccountNonLocked = true;
//    private boolean isCredentialsNonExpired = true;
//    private boolean isEnabled = true;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    public User(String name,
                String surname,
                String username,
                String password,
                Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dateOfBirth = LocalDate.now();
//        System.out.println("User - Deneshen datum:" + dateOfBirth);
    }

    public User() {
    }

    /**
     * najbiten metod, na ovoj nachin spring security vrz baza na data modelot
     * koj shto go imame implementirano doznava koi se' ulogi gi ima korisnikot
     * (tie informacii gi chuvame vo this.role - atributot)
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /**
         * The relationship between the user and the role, spring security,
         * is taking it as a many-to-many relationship; but in this case we are
         * taking as a many-to-one relationship considering that we have to adjust
         * with the role we have, we will insert that in a list.
         */
        return Collections.singletonList(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
