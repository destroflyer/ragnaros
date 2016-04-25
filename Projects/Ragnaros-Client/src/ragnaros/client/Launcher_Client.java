/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import javax.swing.UIManager;

/**

 @author Carl
 */
public class Launcher_Client{
    
    public static void initialize(){
        //LookAndFeel
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex){
        }
    }
}
