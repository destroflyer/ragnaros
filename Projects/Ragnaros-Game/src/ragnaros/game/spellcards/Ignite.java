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
public class Ignite extends SpellCard{

    public Ignite(){
        super(new Spell(){{
                description = new Description("Ignite", "Deal 1 damage to the target monster at the end of each turn, for a total amount of 4 turns.");
                cost = new Cost(new ManaAmount(Mana.RED, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to deal damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                final MonsterCard target = getParameter_Monster(parameters[0]);
                game.addEventListener(new EventListener(){
                    
                    private int remainingTurns = 4;

                    @Override
                    public boolean preEvent(Game game, Event receivedEvent){
                        return true;
                    }

                    @Override
                    public void postEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof TurnEndEvent){
                            game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, -1));
                            remainingTurns--;
                            if((remainingTurns <= 0) || (target.getCurrentLifepoints() <= 0)){
                                game.removeEventListener(this);
                            }
                        }
                    }
                });
            }
        });
    }
}
