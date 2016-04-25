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
public class PutDeckCardInHandEvent extends PutCardInHandEvent{

    public PutDeckCardInHandEvent(Player player, Card card){
        super(player, card);
    }

    @Override
    public void trigger(Game game){
        card.getOwner().getDeck().remove(card);
        super.trigger(game);
    }
}
