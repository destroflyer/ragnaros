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
public class ReturnGraveyardCardToHandEvent extends ReturnToHandEvent{

    public ReturnGraveyardCardToHandEvent(Card card){
        super(card);
    }

    @Override
    public void trigger(Game game){
        card.getOwner().getGraveyard().remove(card);
        super.trigger(game);
    }
}
