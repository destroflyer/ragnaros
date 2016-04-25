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
public class EidolonOfTheGreatRevel extends MonsterCard{

    public EidolonOfTheGreatRevel(){
        description = new Description("Eidolon of the Great Revel", "Whenever a player casts a spell with converted mana cost 3 or less, deal 2 damage to that player.");
        manaTypes = new Mana[]{Mana.RED};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 2;
        setLifepoints(2);
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof CastSpellCardEvent){
            CastSpellCardEvent event = (CastSpellCardEvent) receivedEvent;
            if((cardPosition.getZone() == CardPosition.Zone.MONSTER) && (event.getSpellCard().getSpell().getCost().getMana().getAmount() <= 3)){
                game.triggerEvent(new AddPlayerLifepointsEvent(event.getSpellCard().getOwner(), -2));
            }
        }
    }
}
