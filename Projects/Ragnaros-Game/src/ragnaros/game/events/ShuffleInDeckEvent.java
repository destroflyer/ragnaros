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
public class ShuffleInDeckEvent extends PutCardInDeckEvent{

    protected ShuffleInDeckEvent(Card card){
        super(card.getOwner(), card);
    }

    @Override
    public void trigger(Game game){
        super.trigger(game);
        player.getDeck().shuffle();
    }
}
