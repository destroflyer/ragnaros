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
public class ReturnToHandEvent extends PutCardInHandEvent{

    protected ReturnToHandEvent(Card card){
        super(card.getOwner(), card);
    }
}
