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
    public static <T> TimerSignal<T> every(int timeAmount, TimeUnitsEnum timeUnit, T value){
        return TimerSignal.createTimerSignal(value, timeAmount*timeUnit.getVal());
    }
}
