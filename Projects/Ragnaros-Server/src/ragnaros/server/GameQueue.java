/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.server;

import ragnaros.game.*;
import ragnaros.network.NetworkUtil;
import ragnaros.network.messages.Message_ClientAccepted;
import com.esotericsoftware.kryonet.Server;

/**
 *
 * @author Carl
 */
public class GameQueue{

    public GameQueue(Server server){
        this.server = server;
        clear();
    }
    private Server server;
    private GameQueuePlayer[] queuePlayers;
    private int nextPlayerIndex;
    
    public RunningGame addPlayer(int connectionID, byte[] deckData){
        queuePlayers[nextPlayerIndex] = new GameQueuePlayer(connectionID, deckData);
        server.sendToTCP(connectionID, new Message_ClientAccepted(GameMode.DEFAULT.getClass().getName(), nextPlayerIndex));
        nextPlayerIndex++;
        if(nextPlayerIndex == queuePlayers.length){
            Game game = new Game(true);
            game.setGameMode(GameMode.DEFAULT);
            Player[] players = new Player[queuePlayers.length];
            for(int i=0;i<players.length;i++){
                players[i] = new Player();
            }
            game.setPlayers(players[0], players[1]);
            for(int i=0;i<queuePlayers.length;i++){
                NetworkUtil.unserialize(players[i].getDeck().getPlainTypeSerializer(), queuePlayers[i].getDeck());
                players[i].getDeck().setOwner(players[i]);
            }
            RunningGame runningGame = new RunningGame(server, queuePlayers, game);
            clear();
            return runningGame;
        }
        return null;
    }
    
    private void clear(){
        queuePlayers = new GameQueuePlayer[2];
        nextPlayerIndex = 0;
    }
}
