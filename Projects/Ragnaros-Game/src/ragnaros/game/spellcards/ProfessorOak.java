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
public class ProfessorOak extends SpellCard{

    public ProfessorOak(){
        super(new Spell(){{
                description = new Description("Professor Oak", "Draw until you have at least 6 cards in your hand.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 4));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                while(caster.getOwner().getHand().size() < 6){
                    game.triggerEvent(new DrawEvent(caster.getOwner()));
                }
            }
        });
    }
}
