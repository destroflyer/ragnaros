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
public class Taric extends MonsterCard{

    public Taric(){
        description = new Description("Taric");
        flavorText = "\"Gems.\"";
        manaTypes = new Mana[]{Mana.BLUE};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 2));
        attackDamage = 1;
        setLifepoints(4);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Imbue", "Heal an allied monster for 1 lifepoint.");
                cost = new Cost(new ManaAmount(), true);
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to heal.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, 1));
            }
        }});
    }
}
