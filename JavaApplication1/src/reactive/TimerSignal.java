/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive;

/**
 *
 * @author Andris
 */
class TimerSignal<T> extends Signal<T>{
    
    protected TimerSignal(T value){
        super(value);
    }
    
    public static <T> TimerSignal<T> createTimerSignal(T value){
        return new TimerSignal<>(value);
    }
}
