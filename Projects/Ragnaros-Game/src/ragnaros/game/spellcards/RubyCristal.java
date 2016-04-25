/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class RubyCristal extends SpellCard{

    public RubyCristal(){
        super(new Spell(){{
                description = new Description("Ruby Cristal", "Increase the lifepoints of an allied monster by 2.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the allied monster to strenghten.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddBuffEvent(target, new AdditiveMaximumHealthBuff(2), caster));
            }
        });
    }
}
