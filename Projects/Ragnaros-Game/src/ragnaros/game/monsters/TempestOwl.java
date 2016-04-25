/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class TempestOwl extends MonsterCard{

    public TempestOwl(){
        description = new Description("Tempest Owl");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 1, 1));
        attackDamage = 1;
        setLifepoints(2);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Remove Taunt from a monster.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to remove Taunt from.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL, new MechanicsCardFilter(Taunt.class));
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new RemoveMechanicEvent(target, Taunt.class));
            }
        });
    }
}
