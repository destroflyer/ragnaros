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
public class Slurpy extends MonsterCard{

    public Slurpy(){
        description = new Description("Slurpy", "If this minion survives damage, summon a tapped Slurpy.");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
        attackDamage = 0;
        setLifepoints(2);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if((event.getMonsterCard() == this) && (event.getLifepoints() < 0) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                Slurpy slurpy = new Slurpy();
                if(game.triggerEvent(new SummonMonsterEvent(owner, slurpy))){
                    game.triggerEvent(new TapEvent(slurpy));
                }
            }
        }
    }
}
