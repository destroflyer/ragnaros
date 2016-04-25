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
public class PotOfGreed extends SpellCard{

    public PotOfGreed(){
        super(new Spell(){{
                description = new Description("Pot of Greed", "Draw 2 cards.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                for(int i=0;i<2;i++){
                    game.triggerEvent(new DrawEvent(caster.getOwner()));
                }
            }
        });
    }
}
