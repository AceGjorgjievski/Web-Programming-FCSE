package mk.finki.ukim.mk.lab.model.exeptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super(String.format("The user does not exist. Please register and then login!"));
    }
}
