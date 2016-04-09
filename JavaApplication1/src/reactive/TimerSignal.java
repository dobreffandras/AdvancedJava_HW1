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
    
    private Runnable scheduledAction;
    
    protected TimerSignal(T value, int millisconds){
        super(value);
        this.scheduler.scheduleAtFixedRate(() -> {
            if(TimerSignal.this.scheduledAction != null){
                TimerSignal.this.scheduledAction.run();
            }
        }, millisconds, millisconds, TimeUnit.MILLISECONDS);
    }
    
    public static <T> TimerSignal<T> createTimerSignal(T value,int milliseconds){
        return new TimerSignal<>(value, milliseconds);
    }
    
    public void setScheduledAction(Runnable action){
        this.scheduledAction = action;
    }
    
    public Runnable getScheduledAction(){
        return this.scheduledAction;
    }
}
