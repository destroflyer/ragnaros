/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.spells.*;

/**
 *
 * @author Carl
 */
public class HuntingGrounds extends Land{

    public HuntingGrounds(){
        description = new Description("Hunting Grounds");
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(), true);
        setSpells(
            new TabForManaSpell(new ManaAmount(Mana.GREEN, 1)),
            new Spell(){{
                    description = new Description("Strenghten Beast", "Give an allied beast +1/+1.");
                    cost = new Cost(new ManaAmount(), true);
                    spellParameterFormat = new SpellParameterFormat(){{
                        add("Choose the allied beast to strenghten.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED, new MonsterTribeCardFilter(MonsterCard.Tribe.BEAST));
                    }};
                }

                @Override
                public void cast(Game game, SpellParameter[] parameters){
                    MonsterCard target = getParameter_Monster(parameters[0]);
                    game.triggerEvent(new AddBuffEvent(target, new CompositeBuff(new AdditiveAttackDamageBuff(1), new AdditiveMaximumHealthBuff(1)), caster));
                }
            }
        );
    }
}
