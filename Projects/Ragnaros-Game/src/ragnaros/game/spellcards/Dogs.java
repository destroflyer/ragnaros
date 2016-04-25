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
public class Dogs extends SpellCard{

    public Dogs(){
        super(new Spell(){{
                description = new Description("Dogs", "Much dogs. Very spawn. Wow.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 5));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Dog1()));
                game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Dog2()));
                game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Dog3()));
                game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Dog4()));
                game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), new Dog5()));
            }
        });
    }
}
