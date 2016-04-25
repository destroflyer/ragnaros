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
public class Annie extends MonsterCard{

    public Annie(){
        description = new Description("Annie");
        flavorText = "\"Have you seen my bear Tibbers?\"";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 0;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Summon Tibbers", "Summon Tibbers (5/5, Taunt).");
                cost = new Cost(new ManaAmount(Mana.RED, 3));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                if(!caster.getOwner().getMonsters().contains(tibbers)){
                    game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), tibbers));
                }
            }
        }});
    }
    private Tibbers tibbers = new Tibbers();
}
