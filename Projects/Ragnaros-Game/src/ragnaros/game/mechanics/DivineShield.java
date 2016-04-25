/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.mechanics;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class DivineShield extends Mechanic<MonsterCard>{

    public DivineShield(){
        description = new Description("Divine Shield");
    }

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if((event.getMonsterCard() == target) && (event.getLifepoints() < 0)){
                game.triggerEvent(new RemoveMechanicEvent(target, this));
                return false;
            }
        }
        return super.preEvent(game, receivedEvent);
    }
}
