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
public class KillCommand extends SpellCard{

    public KillCommand(){
        super(new Spell(){{
                description = new Description("Kill Command", "Deal 3 damage to a monster. Deal 5, if you control a Beast.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to deal damage.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                boolean hasBeast = caster.getOwner().getMonsters().containsTribe(MonsterCard.Tribe.BEAST);
                game.triggerEvent(new AddMonsterCurrentLifepointsEvent(target, (hasBeast?-5:-3)));
            }
        });
        manaTypes = new Mana[]{Mana.GREEN};
    }
}
