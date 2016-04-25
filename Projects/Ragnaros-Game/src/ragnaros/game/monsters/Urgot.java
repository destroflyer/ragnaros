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
public class Urgot extends MonsterCard{

    public Urgot(){
        description = new Description("Urgot");
        flavorText = "Maybe viable in THIS game...";
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 3));
        attackDamage = 2;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Exchange", "Choose a monster and swap both its and this cards owner.");
                cost = new Cost(new ManaAmount(Mana.GREEN, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to swap control.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new SwapOwnerEvent(((MonsterCard) caster)));
                game.triggerEvent(new SwapOwnerEvent(target));
            }
        }});
    }
}
