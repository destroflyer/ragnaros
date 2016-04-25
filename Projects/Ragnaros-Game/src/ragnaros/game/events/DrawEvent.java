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
public class DrawEvent extends PutDeckCardInHandEvent{

    public DrawEvent(Player player){
        super(player, player.getDeck().getLast());
    }

    @Override
    public void trigger(Game game){
        if(card != null){
            super.trigger(game);
        }
        else{
            game.triggerEvent(new GameOverEvent(player.getEnemy()));
        }
    }
}
