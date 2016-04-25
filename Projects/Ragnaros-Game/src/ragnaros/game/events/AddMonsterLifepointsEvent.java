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
public class AddMonsterLifepointsEvent extends Event{

    public AddMonsterLifepointsEvent(MonsterCard monsterCard, int lifepoints){
        this.monsterCard = monsterCard;
        this.lifepoints = lifepoints;
    }
    protected MonsterCard monsterCard;
    protected int lifepoints;

    @Override
    public void trigger(Game game){
        if(monsterCard.getCurrentLifepoints() <= 0){
            game.triggerEvent(new DestroyMonsterEvent(monsterCard));
        }
    }

    public MonsterCard getMonsterCard(){
        return monsterCard;
    }

    public int getLifepoints(){
        return lifepoints;
    }
}
