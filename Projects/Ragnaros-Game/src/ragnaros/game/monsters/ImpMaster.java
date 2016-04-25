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
public class ImpMaster extends MonsterCard{

    public ImpMaster(){
        description = new Description("Imp Master", "At the end of your turn, deal 1 damage to this monster and summon a 1/1 Imp.");
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 3));
        attackDamage = 1;
        setLifepoints(5);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof TurnEndEvent){
            TurnEndEvent turnEndEvent = (TurnEndEvent) receivedEvent;
            if((turnEndEvent.getPlayer() == owner) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(this, -1));
                game.triggerEvent(new SummonMonsterEvent(owner, new Imp()));
            }
        }
    }
}
