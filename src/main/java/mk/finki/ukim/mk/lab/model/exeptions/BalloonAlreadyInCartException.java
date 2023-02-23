package mk.finki.ukim.mk.lab.model.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class BalloonAlreadyInCartException extends RuntimeException{

    public BalloonAlreadyInCartException(Long id, String username) {
        super(String.format("Balloon with this id: %d already exists in cart for user with username: %s",id, username));
    }
}
