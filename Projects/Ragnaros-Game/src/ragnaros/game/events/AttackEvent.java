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
public class AttackEvent extends Event{

    public AttackEvent(MonsterCard attackingMonsterCard){
        this.attackingMonsterCard = attackingMonsterCard;
    }
    protected MonsterCard attackingMonsterCard;

    @Override
    public void trigger(Game game){
        attackingMonsterCard.setIsExhausted(true);
    }

    public MonsterCard getAttackingMonsterCard(){
        return attackingMonsterCard;
    }
}
