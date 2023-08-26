package exceptions;

import java.io.Serial;

public class AtributeAlreadyExists extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public AtributeAlreadyExists(String field, String value){
        super("Ya existe un " + field + " con el valor '" + value + "'.");
    }
}
