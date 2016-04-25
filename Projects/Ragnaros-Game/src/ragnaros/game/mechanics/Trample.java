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
public class Trample extends Mechanic<MonsterCard>{

    public Trample(){
        description = new Description("Trample");
    }
    private int tmpCurrentLifepoints;

    @Override
    public boolean preEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if(event.getSource() == target){
                tmpCurrentLifepoints = event.getMonsterCard().getCurrentLifepoints();
            }
        }
        return super.preEvent(game, receivedEvent);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            if(event.getSource() == target){
                int damage = (tmpCurrentLifepoints - target.getAttackDamage());
                if(damage < 0){
                    game.triggerEvent(new AddPlayerLifepointsEvent(event.getMonsterCard().getOwner(), damage));
                }
            }
        }
    }
}
