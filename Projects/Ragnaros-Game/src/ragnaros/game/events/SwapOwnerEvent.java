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
public class SwapOwnerEvent extends Event{

    public SwapOwnerEvent(MonsterCard monsterCard){
        this.monsterCard = monsterCard;
    }
    protected MonsterCard monsterCard;

    @Override
    public void trigger(Game game){
        monsterCard.getOwner().getMonsters().remove(monsterCard);
        monsterCard.setOwner(monsterCard.getOwner().getEnemy());
        monsterCard.getOwner().getMonsters().add(monsterCard);
    }
    
    public MonsterCard getMonsterCard(){
        return monsterCard;
    }
}
