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
public class MultipleAttacksMechanic extends Mechanic<MonsterCard>{

    public MultipleAttacksMechanic(int attacksPerTurn){
        this.attacksPerTurn = attacksPerTurn;
    }
    private int attacksPerTurn;
    private int remainingResetsThisTurn;

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof AttackEvent){
            AttackEvent event = (AttackEvent) receivedEvent;
            if(event.getAttackingMonsterCard() == target){
                if(remainingResetsThisTurn > 0){
                    if(game.triggerEvent(new UntapEvent(target))){
                        target.setIsExhausted(false);
                        remainingResetsThisTurn--;
                    }
                }
            }
        }
        else if(receivedEvent instanceof TurnStartEvent){
            remainingResetsThisTurn = (attacksPerTurn - 1);
        }
    }
}
