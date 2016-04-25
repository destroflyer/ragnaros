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
public class AddMechanicEvent extends Event{

    public AddMechanicEvent(Card target, Mechanic mechanic){
        this(target, mechanic, null);
    }
    
    public AddMechanicEvent(Card target, Mechanic mechanic, Card source){
        this.target = target;
        this.mechanic = mechanic;
        mechanic.setSource(source);
    }
    private Card target;
    private Mechanic mechanic;

    @Override
    public void trigger(Game game){
        target.addMechanics(mechanic);
    }

    public Card getTarget(){
        return target;
    }

    public Mechanic getMechanic(){
        return mechanic;
    }
}
