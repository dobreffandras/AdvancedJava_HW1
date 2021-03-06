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
            if(oldAction!= null) oldAction.run();
            newSignal.setValue(function.apply(this.value));
        };
        return newSignal;
    }
    
    public <U,R> Signal<R> join(Signal<U> other, BiFunction<T, U, R> function){
        Signal<R> newSignal = new Signal<>(function.apply(this.value, other.value));
        
        Runnable thisOldAction = this.action;
        Runnable otherOldAction  = other.action;
        
        this.setAction(() -> {
            if(thisOldAction != null) thisOldAction.run();
            newSignal.setValue(function.apply(this.getValue(), other.getValue()));
        });
        
        other.setAction(() ->{
            if(otherOldAction != null) otherOldAction.run();
            newSignal.setValue(function.apply(this.getValue(), other.getValue()));
        });
        
        return newSignal;
    }
    
    public <U> Signal<U> accumulate(BiFunction<U, T, U> function, U startValue){
        Signal<U> newSignal =  new Signal<>(startValue);
        Runnable oldAction = this.action;
        
        this.action = () -> {
            if(oldAction != null) oldAction.run();
            newSignal.setValue(function.apply(newSignal.getValue(), this.value));
        };
        
        return newSignal;
    }
}
