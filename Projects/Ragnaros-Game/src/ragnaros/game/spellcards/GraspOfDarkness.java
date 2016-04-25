/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class GraspOfDarkness extends SpellCard{

    public GraspOfDarkness(){
        super(new Spell(){{
                description = new Description("Grasp of Darkness", "Give a monster -4/-4 until the end of the turn.");
                cost = new Cost(new ManaAmount(Mana.BLACK, 2));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to weaken.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddBuffEvent(target, new CompositeBuff(new AdditiveAttackDamageBuff(-4), new AdditiveMaximumHealthBuff(-4)){{
                    setRemainingTurns(1);
                }}, caster));
            }
        });
    }
}
