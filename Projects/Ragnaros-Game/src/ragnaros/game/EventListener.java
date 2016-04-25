/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public interface EventListener{
    
    public abstract boolean preEvent(Game game, Event receivedEvent);
    
    public abstract void postEvent(Game game, Event receivedEvent);
}
