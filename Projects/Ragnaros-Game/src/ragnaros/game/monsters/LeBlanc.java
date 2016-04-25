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
public class LeBlanc extends MonsterCard{

    public LeBlanc(){
        description = new Description("LeBlanc", "When this card is attacked, summon a 0/5 LeBlanc clone as new target of the attack - If it gets destroyed by the attack, destroy this card too.");
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 3));
        attackDamage = 1;
        setLifepoints(1);
    }

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof AttackMonsterEvent){
            AttackMonsterEvent event = (AttackMonsterEvent) receivedEvent;
            if(event.getTargetMonsterCard() == this){
                LeBlancClone clone = new LeBlancClone();
                if(game.triggerEvent(new SummonMonsterEvent(owner, clone))){
                    game.triggerEvent(new AttackMonsterEvent(event.getAttackingMonsterCard(), clone));
                    if(clone.getCardPosition().getZone() == CardPosition.Zone.GRAVEYARD){
                        game.triggerEvent(new DestroyMonsterEvent(this));
                    }
                    return false;
                }
            }
        }
        return super.preEvent(game, receivedEvent);
    }
}
