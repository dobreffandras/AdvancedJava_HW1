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
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Writer writer;
        writer = new PrintWriter("output.txt", "UTF-8");
        //writer = new OutputStreamWriter(System.out);
        
        Signal<Integer> timeCounter = Time.every(1, TimeUnitsEnum.SECOND).accumulate((c,x) -> c+1, 0);
         ConsoleReaderSignal.createConsoleReaderSignal().join(timeCounter, 
                (val1, val2) -> {
                    return "Last line on input: "+val1+ 
                    ", time elapsed: "+val2+"s"+ System.lineSeparator();
                }
        ).map((line) -> {
            try {
                writer.write(line);
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
            }
            return line;
        });
        
        
    }
}
