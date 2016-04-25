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
public class RemoveMechanicEvent extends Event{
    
    public RemoveMechanicEvent(Card target, Mechanic mechanic){
        this(target, mechanic.getClass());
    }
    
    public RemoveMechanicEvent(Card target, Class<? extends Mechanic> mechanicClass){
        this.target = target;
        this.mechanicClass = mechanicClass;
    }
    private Card target;
    private Class<? extends Mechanic> mechanicClass;

    @Override
    public void trigger(Game game){
        target.removeMechanics(mechanicClass);
    }

    public Card getTarget(){
        return target;
    }

    public Class<? extends Mechanic> getMechanicClass(){
        return mechanicClass;
    }
}
