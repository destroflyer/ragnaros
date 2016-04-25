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
public class Garen extends MonsterCard{

    public Garen(){
        description = new Description("Garen", "Restores 1 health at the start of each turn.");
        flavorText = "\"Demacia!\"";
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(new int[]{1, 1, 0, 0, 0, 0}));
        attackDamage = 1;
        setLifepoints(3);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(receivedEvent instanceof TurnStartEvent){
            if(currentLifepoints < 3){
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(this, 1));
            }
        }
    }
}
