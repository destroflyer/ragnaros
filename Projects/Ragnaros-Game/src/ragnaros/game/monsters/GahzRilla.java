/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class GahzRilla extends MonsterCard{

    public GahzRilla(){
        description = new Description("Gahz'rilla", "Whenever this monster takes damage, double its attack.");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 8));
        attackDamage = 6;
        setLifepoints(9);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if((event.getMonsterCard() == this) && (event.getLifepoints() < 0)){
                game.triggerEvent(new AddBuffEvent(this, new AdditiveAttackDamageBuff(attackDamage), this));
            }
        }
    }
}
