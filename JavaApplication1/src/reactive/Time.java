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
    public static <T> void every(T value, int milliseconds){
        TimerSignal<T> signal = TimerSignal.createTimerSignal(value);
        signal.setAction(() -> {});   
    }
}
