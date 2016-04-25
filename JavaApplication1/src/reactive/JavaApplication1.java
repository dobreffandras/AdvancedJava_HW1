/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andris
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Writer writer;
        //writer = new PrintWriter("output.txt", "UTF-8");
        writer = new OutputStreamWriter(System.out);
        
        ConsoleReaderSignal readerSignal = ConsoleReaderSignal.createConsoleReaderSignal();
        
        TimerSignal<Long> timerSignal = Time.every(1, TimeUnitsEnum.SECOND);
        Signal<Integer> timeCounter = timerSignal.accumulate((c,x) -> c+1, 0);
        Signal<String> writerSignal = readerSignal.join(timeCounter, 
                (val1, val2) -> {
                    return "Last line on input: "+val1+ 
                    ", time elapsed: "+val2+"s"+
                    System.lineSeparator();
                }
        );
        
        writerSignal.setAction(() -> {
            try {
                writer.write(writerSignal.getValue());
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }
}
