/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.spellcards.*;

/**
 *
 * @author Carl
 */
public class FireDwarf extends MonsterCard{

    public FireDwarf(){
        description = new Description("Fire Dwarf");
        manaTypes = new Mana[]{Mana.RED};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 2;
        setLifepoints(2);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Put a Fireblast spell in your hand for each monster you control.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                int monstersCount = owner.getMonsters().size();
                for(int i=0;i<monstersCount;i++){
                    game.triggerEvent(new PutCardInHandEvent(owner, new Fireblast()));
                }
            }
        });
    }
}
