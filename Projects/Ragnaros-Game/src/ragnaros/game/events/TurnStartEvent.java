/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.events;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class TurnStartEvent extends Event{

    public TurnStartEvent(Player player){
        this.player = player;
    }
    private Player player;

    public Player getPlayer(){
        return player;
    }

    @Override
    public void trigger(Game game){
        game.getGameMode().startTurn(player);
    }
}
