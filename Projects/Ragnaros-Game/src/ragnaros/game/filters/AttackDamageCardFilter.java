/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.filters;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public abstract class AttackDamageCardFilter extends ValueCardFilter<MonsterCard>{

    public AttackDamageCardFilter(){
        super(MonsterCard.class);
    }

    @Override
    protected float getValue(MonsterCard card){
        return card.getAttackDamage();
    }
}
