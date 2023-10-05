package exceptions;

import java.io.Serial;

public class NullPriceException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public NullPriceException(){
        super("Hay un problema con el campo de precio, por favor, intente de nuevo mas tarde.");
    }
}
