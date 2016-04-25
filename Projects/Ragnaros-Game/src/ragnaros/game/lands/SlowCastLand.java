/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;

/**
 *
 * @author Carl
 */
public class SlowCastLand extends Land{

    public SlowCastLand(){
        castCost = new Cost(new ManaAmount(), true);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Return an allied untapped land from the field to your hand.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the land to return.", SpellParameterFormat_Entry.Type.LAND, SpellParameterFormat_Entry.Owner.ALLIED, new TabbedCardFilter(false));
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                Land target = getParameter_Land(parameters[0]);
                game.triggerEvent(new ReturnLandToHandEvent(target));
            }
        });
        isCastSpellObligatory = true;
    }
}
