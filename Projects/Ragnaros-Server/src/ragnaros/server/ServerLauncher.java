/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.server;

import ragnaros.core.Launcher_Core;

/**

 @author Carl
 */
public class ServerLauncher{
    
    public static void main(String[] args){
        Launcher_Core.initialize();
        new MasterServer(33900);
    }
}
