package mk.finki.ukim.mk.lab.model.exeptions;

public class InvalidUserRegisterException extends RuntimeException{

    public InvalidUserRegisterException() {
        super(String.format("You must fill all the black fields in order to register!"));
    }
}
