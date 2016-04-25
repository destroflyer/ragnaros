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
public class DestroyLandEvent extends Event{

    public DestroyLandEvent(Land land){
        this.land = land;
    }
    protected Land land;

    @Override
    public void trigger(Game game){
        land.getOwner().getLands().remove(land);
        game.triggerEvent(new PutCardInGraveyardEvent(land.getOwner(), land));
    }

    public Land getLand(){
        return land;
    }
}
