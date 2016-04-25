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
public class HellsparkElemental extends MonsterCard{

    public HellsparkElemental(){
        description = new Description("Hellspark Elemental", "Destroy this card at the end of the turn.");
        manaTypes = new Mana[]{Mana.RED};
        castCost = new Cost(new ManaAmount(new int[]{1, 0, 2, 0, 0, 0}));
        attackDamage = 4;
        setLifepoints(1);
        hasCharge = true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof TurnEndEvent){
            TurnEndEvent event = (TurnEndEvent) receivedEvent;
            if(cardPosition.getZone() == CardPosition.Zone.MONSTER){
                game.triggerEvent(new DestroyMonsterEvent(this));
            }
        }
    }
}
