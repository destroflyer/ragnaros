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
public class Charmander extends MonsterCard{

    public Charmander(){
        description = new Description("Charmander", "At the start of your turn, replace this monster with Charmeleon.");
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.POKEMON};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 1;
        setLifepoints(1);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof TurnStartEvent){
            TurnStartEvent event = (TurnStartEvent) receivedEvent;
            if((event.getPlayer() == owner) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                game.triggerEvent(new DestroyMonsterEvent(this));
                game.triggerEvent(new SummonMonsterEvent(owner, new Charmeleon()));
            }
        }
    }
}
