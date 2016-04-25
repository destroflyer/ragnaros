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
public class CastFieldCardEvent extends Event{

    public CastFieldCardEvent(Player player, FieldCard fieldCard){
        this.player = player;
        this.fieldCard = fieldCard;
    }
    protected Player player;
    protected FieldCard fieldCard;

    @Override
    public void trigger(Game game){
        fieldCard.setOwner(player);
        fieldCard.onCast();
    }

    public Player getPlayer(){
        return player;
    }
}
