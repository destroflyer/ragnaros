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
public class AraHaan extends MonsterCard{

    public AraHaan(){
        description = new Description("Ara Haan", "At the end of each turn, if this card is on the field, return it to its owners hand.");
        manaTypes = new Mana[]{Mana.BLUE};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 1));
        attackDamage = 3;
        setLifepoints(1);
        hasCharge = true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof TurnEndEvent){
            TurnEndEvent event = (TurnEndEvent) receivedEvent;
            if(cardPosition.getZone() == CardPosition.Zone.MONSTER){
                game.triggerEvent(new ReturnMonsterToHandEvent(this));
            }
        }
    }
}
