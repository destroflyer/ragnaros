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
public class AddLandEvent extends CastFieldCardEvent{

    public AddLandEvent(Player player, Land land){
        super(player, land);
    }

    @Override
    public void trigger(Game game){
        player.getLands().add(fieldCard);
        super.trigger(game);
    }
    
    public Land getLand(){
        return (Land) fieldCard;
    }
}
