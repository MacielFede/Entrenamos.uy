package exceptions;

import java.io.Serial;

public class FebruaryDayException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public FebruaryDayException(){
        super("Si el mes indicado es Febrero (2), el dia indicado no puede ser mayor a 28");
    }
}
