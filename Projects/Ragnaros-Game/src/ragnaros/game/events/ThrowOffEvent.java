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
public class ThrowOffEvent extends Event{

    public ThrowOffEvent(Player player, int handCardIndex){
        this(player.getHand().get(handCardIndex));
    }

    public ThrowOffEvent(Card card){
        this.card = card;
    }
    protected Card card;

    @Override
    public void trigger(Game game){
        card.getOwner().getHand().remove(card);
        card.getOwner().getGraveyard().add(card);
    }

    public Card getCard(){
        return card;
    }
}
