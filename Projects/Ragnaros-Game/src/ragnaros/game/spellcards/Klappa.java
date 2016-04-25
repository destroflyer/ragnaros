/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class Klappa extends SpellCard{

    public Klappa(){
        super(new Spell(){{
                description = new Description("Klappa", ":^)//");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                
            }
        });
    }
}
