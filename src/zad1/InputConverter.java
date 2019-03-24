package zad1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class InputConverter <T> {

    T toConvert;

    InputConverter (T txt)
    {
        this.toConvert = txt;
    }

    <R> R convertBy(Function... stack)
    {
        Object param = toConvert;
        for(Function f: stack)
        {
            param = f.apply(param);
        }
        return (R)param;
    }
}
