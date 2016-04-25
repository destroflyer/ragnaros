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
public class MechanicsCardFilter extends CardFilter{

    public MechanicsCardFilter(Class<? extends Mechanic>... mechanicsClasses){
        this.mechanicsClasses = mechanicsClasses;
    }
    private Class<? extends Mechanic>[] mechanicsClasses;

    @Override
    public boolean isValid(Card card){
        for(Class<? extends Mechanic> mechanicClass : mechanicsClasses){
            if(!card.hasMechanic(mechanicClass)){
                return false;
            }
        }
        return true;
    }
}
