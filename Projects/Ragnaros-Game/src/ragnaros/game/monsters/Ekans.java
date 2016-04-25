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
public class Ekans extends MonsterCard{

    public Ekans(){
        description = new Description("Ekans", "At the start of your turn, replace this monster with Arbok.");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 3;
        setLifepoints(2);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof TurnStartEvent){
            TurnStartEvent event = (TurnStartEvent) receivedEvent;
            if((event.getPlayer() == owner) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                game.triggerEvent(new DestroyMonsterEvent(this));
                game.triggerEvent(new SummonMonsterEvent(owner, new Arbok()));
            }
        }
    }
}
