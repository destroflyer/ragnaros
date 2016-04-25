/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.lands.*;

/**
 *
 * @author Carl
 */
public class BlanchwoodArmor extends SpellCard{

    public BlanchwoodArmor(){
        super(new Spell(){{
                description = new Description("Blanchwood Armor", "Give a monster +1/+1 for each Forest you control.");
                cost = new Cost(new ManaAmount(new int[]{2, 0, 0, 1, 0, 0}));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to strenghten.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                int forests = caster.getOwner().getLands().getAmount(new ClassCardFilter(Forest.class));
                game.triggerEvent(new AddBuffEvent(target, new CompositeBuff(new AdditiveAttackDamageBuff(forests), new AdditiveMaximumHealthBuff(forests)), caster));
            }
        });
    }
}
