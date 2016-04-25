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
public class Mordekaiser extends MonsterCard{

    public Mordekaiser(){
        description = new Description("Mordekaiser", "If this card attacks and kills an enemy monster, summon it on your side of the field.");
        manaTypes = new Mana[]{Mana.BLACK};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 5));
        attackDamage = 4;
        setLifepoints(2);
        hasCharge = true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof AttackMonsterEvent){
            AttackMonsterEvent event = (AttackMonsterEvent) receivedEvent;
            if((event.getAttackingMonsterCard() == this) && (event.getTargetMonsterCard().getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD)){
                if(game.triggerEvent(new ReviveMonsterEvent(owner, event.getTargetMonsterCard()))){
                    game.triggerEvent(new TapEvent(event.getTargetMonsterCard()));
                }
            }
        }
    }
}
