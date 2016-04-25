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
public class Immune extends Mechanic<MonsterCard>{

    public Immune(){
        description = new Description("Immune");
    }

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if((event.getMonsterCard() == target) && (event.getLifepoints() < 0)){
                return false;
            }
        }
        return super.preEvent(game, receivedEvent);
    }
}
