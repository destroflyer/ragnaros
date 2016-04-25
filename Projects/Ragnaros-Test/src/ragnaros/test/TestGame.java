/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.test;

import java.io.File;
import ragnaros.client.*;
import ragnaros.core.Launcher_Core;
import ragnaros.server.MasterServer;

/**
 *
 * @author Carl
 */
public class TestGame{
    
    public static void main(String[] args){
        Launcher_Core.initialize();
        Launcher_Client.initialize();
        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                MasterServer masterServer = new MasterServer(MasterServer.generatePort_Random());
                MainFrame mainFrame1 = new MainFrame(Launcher.connect("localhost", masterServer.getPort()));
                mainFrame1.enterGameQueue(DeckFileHandler.load(new File(PanDeckEditor.DIRECTORY_DECKS + "basic/beasts.xml")));
                mainFrame1.setLocation((int) (mainFrame1.getLocation().getX() - (mainFrame1.getWidth() / 2)), (int) mainFrame1.getLocation().getY());
                mainFrame1.setVisible(true);
                MainFrame mainFrame2 = new MainFrame(Launcher.connect("localhost", masterServer.getPort()));
                mainFrame2.enterGameQueue(DeckFileHandler.load(new File(PanDeckEditor.DIRECTORY_DECKS + "basic/burst.xml")));
                mainFrame2.setLocation((int) (mainFrame2.getLocation().getX() + (mainFrame2.getWidth() / 2)), (int) mainFrame2.getLocation().getY());
                mainFrame2.setVisible(true);
            }
        });
    }
}
