package mk.finki.ukim.mk.lab.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {


    ROLE_USER, ROLE_ADMIN;
    @Override
    public String getAuthority() {
        //se prevzema edna od ulogite koi shto se gore definirani
        return name();
    }
}
