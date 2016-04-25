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
public class TurnEndEvent extends Event{

    public TurnEndEvent(Player player){
        this.player = player;
    }
    private Player player;

    public Player getPlayer(){
        return player;
    }

    @Override
    public void trigger(Game game){
        game.getGameMode().endTurn(player);
        game.nextTurn();
    }
}
