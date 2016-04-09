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
        Signal newSignal = new Signal(function.apply(this.value));
        Runnable oldAction = this.action;
        this.action = () -> {
            oldAction.run();
            newSignal.setValue(function.apply(this.value));
        };
        return newSignal;
    }
    
    public <U,R> Signal<R> join(Signal<U> other, BiFunction<T, U, R> function){
        Signal<R> newSignal = new Signal<>(function.apply(this.value, other.value));
        
        Runnable thisOldAction = this.action;
        Runnable otherOldAction  = other.action;
        
        this.action = () -> {
            thisOldAction.run();
            newSignal.setValue(function.apply(this.value, other.value));
        };
        
        other.action = () ->{
            otherOldAction.run();
            newSignal.setValue(function.apply(this.value, other.value));
        };
        
        return newSignal;
    }
    
    public <U> Signal<U> accumulate(BiFunction<U, T, U> function, U startValue){
        Signal<U> newSignal =  new Signal<>(null);
        Runnable oldAction = this.action;
        
        this.action = () -> {
            oldAction.run();
            if(newSignal.getValue() == null){
                newSignal.setValue(startValue);
            }else{
                newSignal.setValue(function.apply(newSignal.getValue(), this.value));
            }
        };
        
        return newSignal;
    }
}
