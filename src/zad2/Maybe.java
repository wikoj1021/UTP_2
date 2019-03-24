package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    T data;

    static <K> Maybe<K> of(K data) {
        Maybe<K> maybe = new Maybe<>();
        maybe.data = data;
        return maybe;
    }

    void ifPresent(Consumer<T> cons){
        if(null != data) {
            cons.accept(data);
        }
    }

    <D> Maybe<D> map(Function<T, D> func) {
        if(data == null)
            return new Maybe<>();

        return Maybe.of(func.apply(data));
    }

    T get() throws NoSuchElementException {
        if (data == null)
            throw new NoSuchElementException("maybe is empty");
        return data;
    }

    boolean isPresent(){
        return data != null;
    }

    T orElse(T defVal){
        if(data == null)
            return defVal;

        return data;
    }

    Maybe<T> filter(Predicate<T> pred) {
        if(data == null)
            return this;
        if(pred.test(data))
            return this;
        return new Maybe<>();
    }

    @Override
    public String toString() {
        if(data == null)
            return "Maybe is empty";
        return "Maybe has value " + data;
    }
}
