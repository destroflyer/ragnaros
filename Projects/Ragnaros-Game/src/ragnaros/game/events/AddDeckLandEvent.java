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
public class AddDeckLandEvent extends AddLandEvent{

    public AddDeckLandEvent(Player player, Land land){
        super(player, land);
    }

    @Override
    public void trigger(Game game){
        player.getDeck().remove(fieldCard);
        super.trigger(game);
    }
}
