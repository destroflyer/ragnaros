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
public class PokePotion extends SpellCard{

    public PokePotion(){
        super(new Spell(){{
                description = new Description("Poke Potion", "Restore 1 health to a monster. Restore 3, if it is a pokemon.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to heal.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, (target.hasTribe(MonsterCard.Tribe.POKEMON)?3:1)));
            }
        });
    }
}
