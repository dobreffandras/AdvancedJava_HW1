/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Andris
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        
        ConsoleReaderSignal reader = ConsoleReaderSignal.createConsoleReaderSignal();
        Long startupTime = System.currentTimeMillis();
        TimerSignal timer = Time.every(1, TimeUnitsEnum.SECOND, null);
        timer.setAction(() -> {
            writer.write("Last line on input: "+reader.getValue()+ 
                    ", time elapsed: "+(System.currentTimeMillis()-startupTime)/1000+"s"+
                    System.lineSeparator());
            writer.flush();
        });
    }
}
