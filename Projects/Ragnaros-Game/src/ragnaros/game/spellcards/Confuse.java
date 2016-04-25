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
public class Confuse extends SpellCard{

    public Confuse(){
        super(new Spell(){{
                description = new Description("Confuse", "Reduce the attack damage of a monster by 2.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to reduce attack damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddBuffEvent(target, new AdditiveAttackDamageBuff(-2), caster));
            }
        });
    }
}
