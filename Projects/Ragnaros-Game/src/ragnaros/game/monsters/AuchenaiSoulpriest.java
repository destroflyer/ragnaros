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
public class AuchenaiSoulpriest extends MonsterCard{

    public AuchenaiSoulpriest(){
        description = new Description("Auchenai Soulpriest", "Your effects that restore health now deal damage instead.");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 4));
        attackDamage = 3;
        setLifepoints(5);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof AddMonsterCurrentLifepointsEvent){
            AddMonsterCurrentLifepointsEvent event = (AddMonsterCurrentLifepointsEvent) receivedEvent;
            /*if((event.getMonsterCard() == this) && (event.getLifepoints() > 0)){
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(event.getMonsterCard(), -1 * event.getLifepoints()));
            }*/
        }
    }
}
