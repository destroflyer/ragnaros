/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.bots;

import java.util.Stack;
import ragnaros.game.*;
import ragnaros.network.NetworkUtil;

/**
 *
 * @author Carl
 */
public class LocalGameBot extends Bot{

    protected Game localGame = new Game(true);
    protected Stack<byte[]> cachedGames = new Stack<byte[]>();

    @Override
    public void initialize(Game game, CommandSender commandSender){
        super.initialize(game, commandSender);
        localGame.setGameMode(game.getGameMode());
        localGame.setPlayers(new Player(), new Player());
    }
    
    @Override
    public void performTurn(){
        byte[] data = NetworkUtil.serialize(game);
        NetworkUtil.unserialize(localGame, data);
    }
    
    protected void doAction(Command command){
        byte[] data = NetworkUtil.serialize(localGame);
        cachedGames.push(data);
        localGame.executeCommand(command);
    }
    
    protected void undoAction(){
        byte[] data = cachedGames.pop();
        NetworkUtil.unserialize(localGame, data);
    }
    
    protected void executeCommand(Command command){
        localGame.executeCommand(command);
        commandSender.sendCommand(command);
    }
}
