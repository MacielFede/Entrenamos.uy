package exceptions;

import java.io.Serial;

public class SameYearException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public SameYearException(){
        super("El año no puede ser mayor o igual al actual");
    }
}
