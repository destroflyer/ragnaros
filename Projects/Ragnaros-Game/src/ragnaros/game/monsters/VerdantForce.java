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
public class VerdantForce extends MonsterCard{

    public VerdantForce(){
        description = new Description("Verdant Force", "At the start of each turn, summon a 1/1 Saproling.");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(new int[]{5, 0, 0, 3, 0, 0}));
        attackDamage = 7;
        setLifepoints(7);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof TurnStartEvent){
            TurnStartEvent event = (TurnStartEvent) receivedEvent;
            if(cardPosition.getZone() == CardPosition.Zone.MONSTER){
                game.triggerEvent(new SummonMonsterEvent(owner, new Saproling()));
            }
        }
    }
}
