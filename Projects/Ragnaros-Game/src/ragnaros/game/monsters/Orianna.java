/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.lands.*;

/**
 *
 * @author Carl
 */
public class Orianna extends MonsterCard{

    public Orianna(){
        description = new Description("Orianna");
        flavorText = "\"I can make the ticking stop.\"";
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 1));
        attackDamage = 1;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Temporary Mana", "Gain 1 forest until the end of turn.");
                cost = new Cost(new ManaAmount(), true);
            }
            private Forest forest = new Forest();

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                if(game.triggerEvent(new AddLandEvent(owner, forest))){
                    game.addEventListener(new EventListener(){

                        @Override
                        public boolean preEvent(Game game, Event receivedEvent){
                            return true;
                        }

                        @Override
                        public void postEvent(Game game, Event receivedEvent){
                            if(receivedEvent instanceof TurnEndEvent){
                                game.triggerEvent(new RemoveLandEvent(forest));
                                game.removeEventListener(this);
                            }
                        }
                    });
                }
            }
        }});
    }
}
