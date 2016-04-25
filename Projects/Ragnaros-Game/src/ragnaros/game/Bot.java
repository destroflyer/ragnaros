/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public abstract class Bot{

    public Bot(){
        
    }
    protected Game game;
    protected CommandSender commandSender;
    
    public void initialize(Game game, CommandSender commandSender){
        this.game = game;
        this.commandSender = commandSender;
    }
    
    public abstract void performTurn();
}
