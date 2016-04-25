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
public class DiscardEvent extends ThrowOffEvent{

    public DiscardEvent(Player player, int handCardIndex){
        super(player, handCardIndex);
    }

    public DiscardEvent(Card card){
        super(card);
    }
}
