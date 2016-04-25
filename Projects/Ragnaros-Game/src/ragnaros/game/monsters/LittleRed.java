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
public class LittleRed extends MonsterCard{

    public LittleRed(){
        description = new Description("Little Red");
        flavorText = "\"No, I'm not Annie...\"";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 1;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Return Mana", "Choose a land of both you and your opponent and put them back in their owners hand.");
                cost = new Cost(new ManaAmount(), true);
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose one of your lands to return.", SpellParameterFormat_Entry.Type.LAND, SpellParameterFormat_Entry.Owner.ALLIED);
                    add("Choose the enemy lands to return.", SpellParameterFormat_Entry.Type.LAND, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Land ownLand = getParameter_Land(parameters[0]);
                Land enemyLand = getParameter_Land(parameters[1]);
                game.triggerEvent(new ReturnLandToHandEvent(ownLand));
                game.triggerEvent(new ReturnLandToHandEvent(enemyLand));
            }
        }});
    }
}
