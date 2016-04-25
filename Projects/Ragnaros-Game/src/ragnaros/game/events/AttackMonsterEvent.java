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
public class AttackMonsterEvent extends AttackEvent{

    public AttackMonsterEvent(MonsterCard attackingMonsterCard, MonsterCard targetMonsterCard){
        super(attackingMonsterCard);
        this.targetMonsterCard = targetMonsterCard;
    }
    protected MonsterCard targetMonsterCard;

    @Override
    public void trigger(Game game){
        game.getGameMode().attackMonster(attackingMonsterCard, targetMonsterCard);
        super.trigger(game);
    }

    public MonsterCard getTargetMonsterCard(){
        return targetMonsterCard;
    }
}
