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
public class VoyagingSatyr extends MonsterCard{

    public VoyagingSatyr(){
        description = new Description("Voyaging Satyr");
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(new int[]{1, 0, 0, 1, 0, 0}));
        attackDamage = 1;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Untap Land", "Untap a land.");
                cost = new Cost(new ManaAmount(), true);
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the land to untap.", SpellParameterFormat_Entry.Type.LAND, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Land land = getParameter_Land(parameters[0]);
                game.triggerEvent(new UntapEvent(land));
            }
        }});
    }
}
