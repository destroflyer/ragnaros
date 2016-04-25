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
public class Zilean extends MonsterCard{

    public Zilean(){
        description = new Description("Zilean", "When this card is on the field, draw an additional card at the start of your turn.");
        flavorText = "\"I knew you would read that.\"";
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(new int[]{3, 2, 0, 0, 0, 0}));
        attackDamage = 2;
        setLifepoints(3);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof TurnStartEvent){
            TurnStartEvent event = (TurnStartEvent) receivedEvent;
            if((event.getPlayer() == owner) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                game.triggerEvent(new DrawEvent(owner));
            }
        }
    }
}
