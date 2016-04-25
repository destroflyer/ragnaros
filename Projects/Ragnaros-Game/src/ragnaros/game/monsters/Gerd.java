/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Gerd extends MonsterCard{

    public Gerd(){
        description = new Description("Gerd");
        flavorText = "He is op!";
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(new int[]{10, 0, 0, 0, 0, 0}));
        attackDamage = 42;
        setLifepoints(99);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Win Game", "Win the game.");
                cost = new Cost(new ManaAmount(), true);
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new GameOverEvent(owner));
            }
        }});
    }
}
