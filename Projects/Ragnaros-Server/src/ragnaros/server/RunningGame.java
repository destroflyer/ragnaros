/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.server;

import ragnaros.game.Game;
import ragnaros.network.NetworkUtil;
import ragnaros.network.messages.Message_GameUpdate;
import com.esotericsoftware.kryonet.Server;

/**
 *
 * @author Carl
 */
public class RunningGame{

    public RunningGame(Server server, GameQueuePlayer[] queuePlayers, Game game){
        this.server = server;
        this.queuePlayers = queuePlayers;
        this.game = game;
    }
    private Server server;
    private GameQueuePlayer[] queuePlayers;
    private Game game;

    public GameQueuePlayer[] getQueuePlayers(){
        return queuePlayers;
    }

    public Game getGame(){
        return game;
    }
    
    public void sendGameUpdate(){
        sendToTCP(new Message_GameUpdate(NetworkUtil.serialize(game)));
    }
    
    public void sendToTCP(Object message){
        for(GameQueuePlayer queuePlayer : queuePlayers){
            server.sendToTCP(queuePlayer.getConnectionID(), message);
        }
    }
}
