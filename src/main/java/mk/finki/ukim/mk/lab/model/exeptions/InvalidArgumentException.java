package mk.finki.ukim.mk.lab.model.exeptions;



public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String username, String password) {
        super(String.format("Username: %s or Password: %s is invalid",username, password));
    }
}
