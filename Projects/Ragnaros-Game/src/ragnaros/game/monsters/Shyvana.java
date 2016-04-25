/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.monsters.spells.parameter.*;

/**
 *
 * @author Carl
 */
public class Shyvana extends MonsterCard{

    public Shyvana(){
        description = new Description("Shyvana");
        flavorText = "Only the human form, sorry.";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.DRAGON};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 0;
        setLifepoints(2);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Discard", "Select (without viewing) one card in your opponents hand and discard it.");
                cost = new Cost(new ManaAmount(Mana.RED, 2), true);
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the card to remove from your enemy's hand.", SpellParameterFormat_Entry.Type.HAND_CARD, SpellParameterFormat_Entry.Owner.ENEMY);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Player player = getParameter_Player(parameters[0]);
                int handCardIndex = ((HandCardParameter) parameters[0]).getHandCardIndex();
                game.triggerEvent(new DiscardEvent(player, handCardIndex));
            }
        }});
    }
}
