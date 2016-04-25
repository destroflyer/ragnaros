/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.commands;

import ragnaros.game.Command;

/**
 *
 * @author Carl
 */
public class ThrowingOffCommand extends Command{

    public ThrowingOffCommand(){
        
    }

    public ThrowingOffCommand(int handCardIndex){
        this.handCardIndex = handCardIndex;
    }
    private int handCardIndex;

    public int getHandCardIndex(){
        return handCardIndex;
    }
}
