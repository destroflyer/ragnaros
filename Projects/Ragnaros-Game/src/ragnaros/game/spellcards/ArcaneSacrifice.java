/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class ArcaneSacrifice extends SpellCard{

    public ArcaneSacrifice(){
        super(new Spell(){{
                description = new Description("Arcane Sacrifice", "Deal 2 damage to an allied monster and 4 damage to an enemy monster.");
                cost = new Cost(new ManaAmount(Mana.WHITE, 3));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the allied target to damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                    add("Choose the enemy target to damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard alliedTarget = getParameter_Monster(parameters[0]);
                MonsterCard enemyTarget = getParameter_Monster(parameters[1]);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(alliedTarget, -2));
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(enemyTarget, -4));
            }
        });
    }
}
