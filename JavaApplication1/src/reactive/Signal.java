package reactive;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Signal<T> {
    protected T value;
    
    protected Runnable action;
    
    protected Signal(T value){
        this.value = value;
    }
    
    public static <T> Signal<T> createSignal(T value){
        return new Signal<>(value);
    }
    
    public void setValue(T newValue){
        if(action != null)
            action.run();
        this.value = newValue;
    }
    
    public T getValue(){
        return this.value;
    }
    
    public void setAction(Runnable newAction){
        this.action = newAction;
    }
    
    public <U> Signal<U> map(Function<T, U> function){
        return new Signal(function.apply(this.value));
    }
    
    public <U,R> Signal<R> map(Signal<U> other, BiFunction<T, U, R> function){
        return new Signal<>(function.apply(this.value, other.value));
    }
    
    public <U> Signal<U> accumulate(BiFunction<U, T, U> function, U startValue){
        return new Signal<>(function.apply(startValue, this.value));
    }
}
