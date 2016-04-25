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
public class AddBuffEvent extends Event{

    public AddBuffEvent(Card target, Buff buff){
        this(target, buff, null);
    }
    
    public AddBuffEvent(Card target, Buff buff, Card source){
        this.target = target;
        this.buff = buff;
        buff.setSource(source);
    }
    private Card target;
    private Buff buff;

    @Override
    public void trigger(Game game){
        target.addBuff(buff);
    }

    public Card getTarget(){
        return target;
    }

    public Buff getBuff(){
        return buff;
    }
}
