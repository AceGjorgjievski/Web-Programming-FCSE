package mk.finki.ukim.mk.lab.model.exeptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username) {
        super(String.format("The username %s already exists!", username));
    }
}
