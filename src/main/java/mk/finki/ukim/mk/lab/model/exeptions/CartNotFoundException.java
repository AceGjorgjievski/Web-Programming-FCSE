package mk.finki.ukim.mk.lab.model.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(Long id) {
        super(String.format("Cart was not found with this id: %ld", id));
    }
}
