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
public class Smite extends SpellCard{

    public Smite(){
        super(new Spell(){{
                description = new Description("Smite", "Deal 3 damage to an enemy monster.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to deal damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, -3));
            }
        });
    }
}
