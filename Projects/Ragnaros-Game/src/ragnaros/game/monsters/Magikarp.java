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
public class Magikarp extends MonsterCard{

    public Magikarp(){
        description = new Description("Magikarp", "At the start of your third turn after summoning this monster, replace it with Gyarados.");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.FISH};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 1));
        attackDamage = 0;
        setLifepoints(1);
    }
    private int remainingEvolutionTurns;

    @Override
    public void onCast(){
        super.onCast();
        remainingEvolutionTurns = 3;
    }

    @Override
    public void postEvent(Game game, Event receivedEvent){
        if(receivedEvent instanceof TurnStartEvent){
            TurnStartEvent event = (TurnStartEvent) receivedEvent;
            if((event.getPlayer() == owner) && (cardPosition.getZone() == CardPosition.Zone.MONSTER)){
                remainingEvolutionTurns--;
                if(remainingEvolutionTurns <= 0){
                    game.triggerEvent(new DestroyMonsterEvent(this));
                    game.triggerEvent(new SummonMonsterEvent(owner, new Gyarados()));
                }
            }
        }
    }
}
