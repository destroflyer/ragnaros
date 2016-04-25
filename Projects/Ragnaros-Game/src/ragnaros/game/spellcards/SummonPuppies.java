/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.monsters.*;

/**
 *
 * @author Carl
 */
public class SummonPuppies extends SpellCard{

    public SummonPuppies(){
        super(new Spell(){{
                description = new Description("Summon Puppies", "Summon 3 kawaii puppies.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                for(int i=0;i<3;i++){
                    game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Puppy()));
                }
            }
        });
    }
}
