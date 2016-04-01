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
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConsoleReaderSignal reader = ConsoleReaderSignal.createConsoleReaderSignal();
        Long startupTime = System.currentTimeMillis();
        TimerSignal timer = Time.every(1, TimeUnitsEnum.SECOND, null);
        timer.setAction(() -> {
            System.out.println("Last line on input: "+reader.getValue()+
                    ", time elapsed: "+(System.currentTimeMillis()-startupTime)/1000+"s");
        });
    }
    
}
