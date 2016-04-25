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
public class DestroyMonsterEvent extends Event{

    public DestroyMonsterEvent(MonsterCard monsterCard){
        this.monsterCard = monsterCard;
    }
    protected MonsterCard monsterCard;

    @Override
    public void trigger(Game game){
        monsterCard.getOwner().getMonsters().remove(monsterCard);
        monsterCard.setCurrentLifepoints(monsterCard.getMaximumLifepoints());
        game.triggerEvent(new PutCardInGraveyardEvent(monsterCard.getOwner(), monsterCard));
        monsterCard.onDestroyed();
    }

    public MonsterCard getMonsterCard(){
        return monsterCard;
    }
}
