/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive;

import java.util.Scanner;

/**
 *
 * @author Andris
 */
public class ConsoleReaderSignal extends Signal<String>{
    
    protected ConsoleReaderSignal() {
        super(null);
        
        new Thread(() -> {
            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()) {
                String input = scan.nextLine();
                this.setValue(input);
            }
        }).start();
        
    }
    
    public static ConsoleReaderSignal createConsoleReaderSignal(){
        return new ConsoleReaderSignal();
    }
    
}
