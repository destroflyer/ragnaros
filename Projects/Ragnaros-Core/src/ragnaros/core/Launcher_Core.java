/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.core;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import ragnaros.core.files.FileAssets;

/**

 @author Carl
 */
public class Launcher_Core{
    
    public static void initialize(){
        //ConsoleOutput
        Logger.getLogger("").setLevel(Level.SEVERE);
        try{
            FileOutputStream logFileOutputStream = new FileOutputStream("./log.txt");
            System.setOut(new PrintStream(new MultipleOutputStream(System.out, logFileOutputStream)));
            System.setErr(new PrintStream(new MultipleOutputStream(System.err, logFileOutputStream)));
        }catch(Exception ex){
        }
        FileAssets.readRootFile();
    }
}
