package exceptions;

import java.io.Serial;

public class UserAlreadySignedUpToClassException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public UserAlreadySignedUpToClassException(){
        super("El usuario ingresado ya esta registrado para en la clase indicada");
    }
}
