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
public class AddMonsterCurrentLifepointsEvent extends AddMonsterLifepointsEvent{

    public AddMonsterCurrentLifepointsEvent(MonsterCard monsterCard, int lifepoints){
        this(monsterCard, lifepoints, null);
    }

    public AddMonsterCurrentLifepointsEvent(MonsterCard monsterCard, int lifepoints, Card source){
        super(monsterCard, lifepoints);
        this.source = source;
    }
    private Card source;

    @Override
    public void trigger(Game game){
        monsterCard.setCurrentLifepoints(monsterCard.getCurrentLifepoints() + lifepoints);
        super.trigger(game);
    }

    public Card getSource(){
        return source;
    }
}
