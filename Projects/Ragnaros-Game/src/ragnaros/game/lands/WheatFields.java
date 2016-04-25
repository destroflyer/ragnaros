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
public class WheatFields extends Land{

    public WheatFields(){
        description = new Description("Wheat Fields");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(), true);
        setSpells(
            new Spell(){{
                    description = new Description(Description.DEFAULT_TITLE, "Put a random white land from your deck into play and shuffle this card in your deck.");
                    cost = new Cost(new ManaAmount(), true);
                }

                @Override
                public void cast(Game game, SpellParameter[] parameters){
                    Land land = (Land) owner.getDeck().getRandom(new ManaTypeCardFilter(Mana.WHITE), new ClassCardFilter(Land.class));
                    if(land != null){
                        if(game.triggerEvent(new AddDeckLandEvent(owner, land))){
                            game.triggerEvent(new ShuffleLandInDeckEvent(WheatFields.this));
                        }
                    }
                }
            }
        );
    }
}
