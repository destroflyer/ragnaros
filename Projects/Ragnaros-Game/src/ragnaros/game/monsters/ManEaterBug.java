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
public class ManEaterBug extends MonsterCard{

    public ManEaterBug(){
        description = new Description("Man-Eater Bug");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BUG};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 1), true);
        attackDamage = 1;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Monster Destroyer", "Destroy an enemy monster.");
                cost = new Cost(new ManaAmount(Mana.GREEN, 1), true);
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to destroy.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new DestroyMonsterEvent(target));
            }
        }});
    }
}
