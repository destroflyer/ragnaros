/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.test;

import ragnaros.client.*;
import ragnaros.core.Launcher_Core;
import ragnaros.server.MasterServer;

/**
 *
 * @author Carl
 */
public class TestClient{
    
    public static void main(String[] args){
        Launcher_Core.initialize();
        Launcher_Client.initialize();
        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                MasterServer masterServer = new MasterServer(33900);
                MainFrame mainFrame = new MainFrame(Launcher.connect("localhost", masterServer.getPort()));
                mainFrame.setVisible(true);
            }
        });
    }
}
