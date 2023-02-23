package mk.finki.ukim.mk.lab.model.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super(String.format("User was not found with this username %s",username));
    }
}
