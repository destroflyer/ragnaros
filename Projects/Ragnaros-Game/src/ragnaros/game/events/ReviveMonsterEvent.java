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
public class ReviveMonsterEvent extends SummonMonsterEvent{

    public ReviveMonsterEvent(Player player, MonsterCard monsterCard){
        super(player, monsterCard);
    }

    @Override
    public void trigger(Game game){
        fieldCard.getOwner().getGraveyard().remove(fieldCard);
        super.trigger(game);
    }
}
