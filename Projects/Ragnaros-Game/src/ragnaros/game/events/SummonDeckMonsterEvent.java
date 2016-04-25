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
public class SummonDeckMonsterEvent extends SummonMonsterEvent{

    public SummonDeckMonsterEvent(Player player, MonsterCard monsterCard){
        super(player, monsterCard);
    }

    @Override
    public void trigger(Game game){
        fieldCard.getOwner().getDeck().remove(fieldCard);
        super.trigger(game);
    }
}
