/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Ditto extends MonsterCard{

    public Ditto(){
        description = new Description("Ditto");
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.POKEMON};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 4));
        attackDamage = 0;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Transform");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to copy.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new DestroyMonsterEvent((MonsterCard) caster));
                game.triggerEvent(new SummonMonsterEvent(owner, (MonsterCard) target.clone()));
            }
        }});
    }
}
