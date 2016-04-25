/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.buffs;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class CompositeBuff<T extends Card> extends Buff<T>{

    public CompositeBuff(Buff<T>... buffs){
        this.buffs = buffs;
    }
    private Buff<T>[] buffs;
    
    @Override
    public void onAdded(T target){
        super.onAdded(target);
        for(Buff buff : buffs){
            buff.onAdded(target);
        }
    }
    
    @Override
    public void onRemoved(T target){
        super.onRemoved(target);
        for(Buff buff : buffs){
            buff.onRemoved(target);
        }
    }
}
