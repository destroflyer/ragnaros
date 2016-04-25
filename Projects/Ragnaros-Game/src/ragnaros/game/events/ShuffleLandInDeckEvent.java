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
public class ShuffleLandInDeckEvent extends ShuffleInDeckEvent{

    public ShuffleLandInDeckEvent(Land land){
        super(land);
    }

    @Override
    public void trigger(Game game){
        card.getOwner().getLands().remove(card);
        super.trigger(game);
    }
}
