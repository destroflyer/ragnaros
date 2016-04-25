/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class SunfuryProtector extends MonsterCard{

    public SunfuryProtector(){
        description = new Description("Sunfury Protector");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
        attackDamage = 2;
        setLifepoints(3);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Give an allied monster Taunt.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to give Taunt.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMechanicEvent(target, new Taunt(), caster));
            }
        });
    }
}
