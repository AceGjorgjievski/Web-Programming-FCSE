package mk.finki.ukim.mk.lab.model.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BalloonNotFoundException extends RuntimeException{
    public BalloonNotFoundException(Long id) {
        super(String.format("Balloon was not found with the manufacturer id: %d", id));
    }
}
