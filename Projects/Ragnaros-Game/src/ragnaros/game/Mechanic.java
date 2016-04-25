/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class Mechanic<T extends Card> implements EventListener{
    
    public static Class[] DYNAMIC_MECHANICS_CLASSES = new Class[]{
        Taunt.class,
        DivineShield.class,
        Windfury.class,
        Immune.class,
        Trample.class
    };
    protected Description description = new Description();
    protected T source;
    protected T target;

    public Description getDescription(){
        return description;
    }

    public void setSource(T source){
        this.source = source;
    }

    public void setTarget(T target){
        this.target = target;
    }

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        return true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        
    }
}
