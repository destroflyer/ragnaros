/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import java.util.HashMap;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Buff<T extends Card>{

    protected Card source;
    private int remainingTurns = -1;
    private HashMap<T, EventListener> activeEventListeners = new HashMap<T, EventListener>();
    
    public void onAdded(final T target){
        if(remainingTurns != -1){
            EventListener eventListener = new EventListener(){

                @Override
                public boolean preEvent(Game game, Event receivedEvent){
                    return true;
                }

                @Override
                public void postEvent(Game game, Event receivedEvent){
                    if(receivedEvent instanceof TurnEndEvent){
                        remainingTurns--;
                        if(remainingTurns <= 0){
                            game.triggerEvent(new RemoveBuffEvent(target, Buff.this));
                        }
                    }
                }
            };
            activeEventListeners.put(target, eventListener);
            source.getGame().addEventListener(eventListener);
        }
    }
    
    public void onRemoved(T target){
        EventListener eventListener = activeEventListeners.get(target);
        if(eventListener != null){
            source.getGame().removeEventListener(eventListener);
        }
    }

    public void setSource(Card source){
        this.source = source;
    }

    public Card getSource(){
        return source;
    }

    public void setRemainingTurns(int remainingTurns){
        this.remainingTurns = remainingTurns;
    }

    public int getRemainingTurns(){
        return remainingTurns;
    }
}
