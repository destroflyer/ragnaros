/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public abstract class Aura<T extends Card> extends Mechanic<T>{

    protected Aura(Buff buff){
        this.buff = buff;
    }
    private Buff buff;
    private boolean listenToEvents = true;

    public Buff getBuff(){
        return buff;
    }

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        return true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        eventUpdate(game);
    }
    
    private void eventUpdate(Game game){
        if(listenToEvents){
            boolean isActive = isActive();
            for(Card card : game.getCards()){
                boolean shouldHaveBuff = (isActive && isAffected(card));
                boolean hasBuff = card.hasBuff(buff);
                if(hasBuff != shouldHaveBuff){
                    listenToEvents = false;
                    if(shouldHaveBuff){
                        game.triggerEvent(new AddBuffEvent(card, buff));
                    }
                    else{
                        game.triggerEvent(new RemoveBuffEvent(card, buff));
                    }
                    listenToEvents = true;
                }
            }
        }
    }
    
    public abstract boolean isActive();
    
    public abstract boolean isAffected(Card card);
}
