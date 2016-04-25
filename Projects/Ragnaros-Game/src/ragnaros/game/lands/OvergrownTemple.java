/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;

/**
 *
 * @author carl
 */
public class OvergrownTemple extends Land{

    public OvergrownTemple(){
        description = new Description("Overgrown Temple");
        manaTypes = new Mana[]{Mana.GREEN};
        setSpells(
            new ManaSpell(){{
                    description = new Description(Description.DEFAULT_TITLE, "Tap an allied land.");
                    cost = new Cost(new ManaAmount(), true);
                    spellParameterFormat = new SpellParameterFormat(){{
                        add("Choose the allied land to tap.", SpellParameterFormat_Entry.Type.LAND, SpellParameterFormat_Entry.Owner.ALLIED, new TabbedCardFilter(false));
                    }};
                }
                private ManaAmount gainedManaAmount = new ManaAmount(Mana.GREEN, 1);

                @Override
                public void cast(Game game, SpellParameter[] parameters){
                    Land land = getParameter_Land(parameters[0]);
                    if(game.triggerEvent(new TapEvent(land))){
                        game.triggerEvent(new AddPlayerManaEvent(owner, gainedManaAmount));
                    }
                }

                @Override
                public ManaAmount getGainedManaAmount() {
                    return gainedManaAmount;
                }
            }
        );
    }
}
