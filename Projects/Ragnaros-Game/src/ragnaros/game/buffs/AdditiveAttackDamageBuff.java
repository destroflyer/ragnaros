/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.buffs;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class AdditiveAttackDamageBuff extends Buff<MonsterCard>{

    public AdditiveAttackDamageBuff(int attackDamage){
        this.attackDamage = attackDamage;
    }
    private int attackDamage;
    
    @Override
    public void onAdded(MonsterCard target){
        super.onAdded(target);
        target.getGame().triggerEvent(new AddMonsterAttackDamageEvent(target, attackDamage));
    }
    
    @Override
    public void onRemoved(MonsterCard target){
        super.onRemoved(target);
        target.getGame().triggerEvent(new AddMonsterAttackDamageEvent(target, -1 * attackDamage));
    }
}
