/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class LlanowarBehemoth extends MonsterCard{

    public LlanowarBehemoth(){
        description = new Description("Llanowar Behemoth");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(new int[]{3, 0, 0, 2, 0, 0}));
        attackDamage = 4;
        setLifepoints(4);
        setSpells(new Spell[]{new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Tap an allied monster and get +1/+1 until the end of the turn.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the allied monster to tap.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                if(game.triggerEvent(new TapEvent(target))){
                    game.triggerEvent(new AddBuffEvent(caster, new CompositeBuff(new AdditiveAttackDamageBuff(1), new AdditiveMaximumHealthBuff(1)){{
                        setRemainingTurns(1);
                    }}, caster));
                }
            }
        }});
    }
}
