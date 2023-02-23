package mk.finki.ukim.mk.lab.model.exeptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException() {
        super(String.format("The following passwords do not match!"));
    }
}
