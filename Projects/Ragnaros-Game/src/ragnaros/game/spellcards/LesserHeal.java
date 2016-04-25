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
public class LesserHeal extends SpellCard{

    public LesserHeal(){
        super(new Spell(){{
                description = new Description("Lesser Heal", "Restore 2 health to a monster.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to heal.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, 2));
            }
        });
    }
}
