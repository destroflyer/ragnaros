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
public class GoblinGuide extends MonsterCard{

    public GoblinGuide(){
        description = new Description("Goblin Guide", "Whenever this monster attacks, your opponent draws the top card of his deck if it's a land.");
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.GOBLIN};
        castCost = new Cost(new ManaAmount(Mana.RED, 1));
        attackDamage = 2;
        setLifepoints(2);
        hasCharge = true;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AttackEvent){
            AttackEvent event = (AttackEvent) receivedEvent;
            if(event.getAttackingMonsterCard() == this){
                Card card = owner.getEnemy().getDeck().getLast();
                if(card instanceof Land){
                    game.triggerEvent(new PutDeckCardInHandEvent(owner.getEnemy(), card));
                }
            }
        }
    }
}
