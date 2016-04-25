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
public class Katarina extends MonsterCard{

    public Katarina(){
        description = new Description("Katarina", "Untap this card when an enemy monster dies.");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(new int[]{0, 0, 0, 0, 0, 2}));
        attackDamage = 1;
        setLifepoints(2);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        super.postEvent(game, receivedEvent);
        if(cardPosition.getZone() == CardPosition.Zone.MONSTER){
            if(receivedEvent instanceof DestroyMonsterEvent){
                DestroyMonsterEvent event = (DestroyMonsterEvent) receivedEvent;
                if(event.getMonsterCard().getOwner() != owner){
                    game.triggerEvent(new UntapEvent(this));
                }
            }
        }
    }
}
