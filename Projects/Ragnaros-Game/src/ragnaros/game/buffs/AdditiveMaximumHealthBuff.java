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
public class AdditiveMaximumHealthBuff extends Buff<MonsterCard>{

    public AdditiveMaximumHealthBuff(int lifepoints){
        this.lifepoints = lifepoints;
    }
    private int lifepoints;
    
    @Override
    public void onAdded(MonsterCard target){
        super.onAdded(target);
        target.getGame().triggerEvent(new AddMonsterMaximumLifepointsEvent(target, lifepoints));
    }
    
    @Override
    public void onRemoved(MonsterCard target){
        super.onRemoved(target);
        target.getGame().triggerEvent(new AddMonsterMaximumLifepointsEvent(target, -1 * lifepoints));
    }
}
