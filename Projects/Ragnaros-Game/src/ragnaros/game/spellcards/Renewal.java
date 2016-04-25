/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Renewal extends SpellCard{

    public Renewal(){
        super(new Spell(){{
                description = new Description("Renewal", "Heal an allied monster for 2 lifepoints. If it is still damaged, return this card to your hand at the end of the turn.");
                cost = new Cost(new ManaAmount(Mana.WHITE, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to heal.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                final MonsterCard target = getParameter_Monster(parameters[0]);
                if(game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, 2))){
                    if(target.getCurrentLifepoints() < target.getMaximumLifepoints()){
                        game.addEventListener(new EventListener(){

                            @Override
                            public boolean preEvent(Game game, Event receivedEvent){
                                return true;
                            }

                            @Override
                            public void postEvent(Game game, Event receivedEvent){
                                if(receivedEvent instanceof TurnEndEvent){
                                    game.triggerEvent(new ReturnGraveyardCardToHandEvent(caster));
                                    game.removeEventListener(this);
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
