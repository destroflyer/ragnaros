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
public class ReturnLandToHandEvent extends ReturnToHandEvent{

    public ReturnLandToHandEvent(Land land){
        super(land);
    }

    @Override
    public void trigger(Game game){
        card.getOwner().getLands().remove(card);
        super.trigger(game);
    }
}
