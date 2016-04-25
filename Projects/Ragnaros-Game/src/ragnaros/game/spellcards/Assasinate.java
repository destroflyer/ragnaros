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
public class Assasinate extends SpellCard{

    public Assasinate(){
        super(new Spell(){{
                description = new Description("Assasinate", "Destroy a tapped enemy monster.");
                cost = new Cost(new ManaAmount(Mana.BLACK, 3));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the tapped target to detroy.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                if(target.isTapped()){
                    game.triggerEvent(new DestroyMonsterEvent(target));
                }
            }
        });
    }
}
