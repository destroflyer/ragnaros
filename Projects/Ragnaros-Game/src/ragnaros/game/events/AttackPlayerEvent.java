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
public class AttackPlayerEvent extends AttackEvent{

    public AttackPlayerEvent(MonsterCard attackingMonsterCard, Player targetPlayer){
        super(attackingMonsterCard);
        this.targetPlayer = targetPlayer;
    }
    protected Player targetPlayer;

    @Override
    public void trigger(Game game){
        game.triggerEvent(new AddPlayerLifepointsEvent(targetPlayer, -1 * attackingMonsterCard.getAttackDamage()));
        super.trigger(game);
    }

    public Player getTargetPlayer(){
        return targetPlayer;
    }
}
