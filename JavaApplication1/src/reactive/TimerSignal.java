/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Andris
 */
public class TimerSignal<T> extends Signal<T>{
    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    protected TimerSignal(T value, int millisconds){
        super(value);
        this.scheduler.scheduleAtFixedRate(() -> {
            if(TimerSignal.this.action != null){
                TimerSignal.this.action.run();
            }
        }, millisconds, millisconds, TimeUnit.MILLISECONDS);
    }
    
    public static <T> TimerSignal<T> createTimerSignal(T value,int milliseconds){
        return new TimerSignal<>(value, milliseconds);
    }
}
