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
public class AddPlayerManaEvent extends Event{

    public AddPlayerManaEvent(Player player, ManaAmount manaAmount){
        this.player = player;
        this.manaAmount = manaAmount;
    }
    private Player player;
    private ManaAmount manaAmount;

    @Override
    public void trigger(Game game){
        player.getAvailableMana().add(manaAmount);
    }

    public Player getPlayer(){
        return player;
    }

    public ManaAmount getManaAmount(){
        return manaAmount;
    }
}
