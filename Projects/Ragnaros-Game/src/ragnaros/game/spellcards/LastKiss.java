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
public class LastKiss extends SpellCard{

    public LastKiss(){
        super(new Spell(){{
                description = new Description("Last Kiss", "Deal 2 damage to a monster and gain 2 lifepoints.");
                cost = new Cost(new ManaAmount(new int[]{2, 0, 0, 0, 0, 1}));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to deal damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, -2));
                game.triggerEvent(new AddPlayerLifepointsEvent(caster.getOwner(), 2));
            }
        });
    }
}
