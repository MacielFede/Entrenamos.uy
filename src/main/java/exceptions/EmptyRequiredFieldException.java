package exceptions;

import java.io.Serial;

public class EmptyRequiredFieldException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public EmptyRequiredFieldException(String field){
        super("El campo " + field + " no puede ser dejado en blanco");
    }
}
