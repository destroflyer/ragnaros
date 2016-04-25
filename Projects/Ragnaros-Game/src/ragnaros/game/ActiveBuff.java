/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class ActiveBuff implements EventListener{

    public ActiveBuff(Card target, Buff buff){
        this.target = target;
        this.buff = buff;
    }
    protected Card target;
    private Buff buff;
    
    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        return true;
    }
    
    @Override
    public void postEvent(Game game, Event receivedEvent){
        
    }

    public Card getTarget(){
        return target;
    }

    public Buff getBuff(){
        return buff;
    }
}
