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
public class Time {
    public static TimerSignal<Long> every(int timeAmount, TimeUnitsEnum timeUnit){
        TimerSignal<Long> timerSignal = TimerSignal.createTimerSignal(new Long(0), timeAmount*timeUnit.getVal());
        Long startupTime = System.currentTimeMillis();
        timerSignal.setScheduledAction(() -> {
            timerSignal.setValue((System.currentTimeMillis()-startupTime)/1000);
        });
        return timerSignal;
    }
}
