package mk.finki.ukim.mk.lab.model.exeptions;

public class InvalidUsernameOrPasswordException extends Exception{

    public InvalidUsernameOrPasswordException() {
        super(String.format("Invalid credentials"));
    }
}
