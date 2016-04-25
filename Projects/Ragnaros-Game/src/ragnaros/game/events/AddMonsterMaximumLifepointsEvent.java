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
public class AddMonsterMaximumLifepointsEvent extends AddMonsterLifepointsEvent{

    public AddMonsterMaximumLifepointsEvent(MonsterCard monsterCard, int lifepoints){
        super(monsterCard, lifepoints);
    }

    @Override
    public void trigger(Game game){
        monsterCard.setMaximumLifepoints(monsterCard.getMaximumLifepoints() + lifepoints);
        super.trigger(game);
    }
}
