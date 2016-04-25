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
public class Yorick extends MonsterCard{

    public Yorick(){
        description = new Description("Yorick");
        flavorText = "\"I need a catlady skin.\"";
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 2));
        attackDamage = 1;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Ghoul Spawner", "Summon a 1/1 Ghoul.");
                cost = new Cost(new ManaAmount(Mana.BLACK, 1), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Ghoul()));
            }
        }});
    }
}
