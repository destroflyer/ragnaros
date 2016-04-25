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
public class RemoveBuffEvent extends Event{
    
    public RemoveBuffEvent(Card target, Buff buff){
        this.target = target;
        this.buff = buff;
    }
    private Card target;
    private Buff buff;

    @Override
    public void trigger(Game game){
        target.removeBuff(buff);
    }

    public Card getTarget(){
        return target;
    }

    public Buff getBuff(){
        return buff;
    }
}
