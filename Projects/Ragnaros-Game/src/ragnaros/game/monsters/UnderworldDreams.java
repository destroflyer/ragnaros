/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class UnderworldDreams extends MonsterCard{

    public UnderworldDreams(){
        description = new Description("Underworld Dreams", "While this card is on the field, deal 1 damage to a player each time he draws a card.");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(new int[]{1, 0, 0, 0, 0, 2}));
        attackDamage = 0;
        setLifepoints(6);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof DrawEvent){
            DrawEvent event = (DrawEvent) receivedEvent;
            if(cardPosition.getZone() == CardPosition.Zone.MONSTER){
                game.triggerEvent(new AddPlayerLifepointsEvent(event.getPlayer(), -1));
            }
        }
    }
}
