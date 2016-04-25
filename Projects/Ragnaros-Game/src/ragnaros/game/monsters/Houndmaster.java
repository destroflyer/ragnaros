/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class Houndmaster extends MonsterCard{

    public Houndmaster(){
        description = new Description("Houndmaster");
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 4));
        attackDamage = 4;
        setLifepoints(3);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Give an allied beast +2/+2 and Taunt.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the allied beast to strenghten.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED, new MonsterTribeCardFilter(Tribe.BEAST));
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddBuffEvent(target, new CompositeBuff(new AdditiveAttackDamageBuff(2), new AdditiveMaximumHealthBuff(2)), caster));
                game.triggerEvent(new AddMechanicEvent(target, new Taunt(), caster));
            }
        });
    }
}
