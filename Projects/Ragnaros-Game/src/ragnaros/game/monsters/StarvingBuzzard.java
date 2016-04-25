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
public class StarvingBuzzard extends MonsterCard{

    public StarvingBuzzard(){
        description = new Description("Starving Buzzard", "Whenever you summon a Beast, draw a card.");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 4));
        attackDamage = 3;
        setLifepoints(2);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof SummonMonsterEvent){
            SummonMonsterEvent event = (SummonMonsterEvent) receivedEvent;
            if((event.getPlayer() == owner) && (event.getMonsterCard().hasTribe(Tribe.BEAST)) && (event.getMonsterCard() != this) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                game.triggerEvent(new DrawEvent(owner));
            }
        }
    }
}
