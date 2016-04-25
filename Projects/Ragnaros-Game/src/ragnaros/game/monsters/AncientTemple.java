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
public class AncientTemple extends MonsterCard{

    public AncientTemple(){
        description = new Description("Ancient Temple", "Whenever this monster is healed, it gains 2 lifepoints.");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 2));
        attackDamage = 0;
        setLifepoints(2);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if((event.getMonsterCard() == this) && (event.getLifepoints() > 0)){
                game.triggerEvent(new AddMonsterMaximumLifepointsEvent(this, 2));
            }
        }
    }
}
