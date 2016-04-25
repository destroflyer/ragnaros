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
public class RemoveLandEvent extends Event{

    public RemoveLandEvent(Land land){
        this.land = land;
    }
    protected Land land;

    @Override
    public void trigger(Game game){
        land.getOwner().getLands().remove(land);
        land.getOwner().getGraveyard().add(land);
    }
}
