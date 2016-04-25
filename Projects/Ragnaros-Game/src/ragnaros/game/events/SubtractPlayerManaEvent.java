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
public class SubtractPlayerManaEvent extends Event{

    public SubtractPlayerManaEvent(Player player, ManaAmount manaAmount){
        this.player = player;
        this.manaAmount = manaAmount;
    }
    private Player player;
    private ManaAmount manaAmount;

    @Override
    public void trigger(Game game){
        player.getAvailableMana().subtractFromAvailable(manaAmount);
    }

    public Player getPlayer(){
        return player;
    }

    public ManaAmount getManaAmount(){
        return manaAmount;
    }
}
