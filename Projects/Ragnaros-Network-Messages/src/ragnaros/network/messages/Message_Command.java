/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

import ragnaros.game.Command;

/**
 *
 * @author Carl
 */
public class Message_Command{
    
    public Message_Command(){
        
    }
    
    public Message_Command(Command command){
        this.command = command;
    }
    private Command command;

    public Command getCommand(){
        return command;
    }
}
