/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class Sunwalker extends MonsterCard{

    public Sunwalker(){
        description = new Description("Sunwalker");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 6));
        attackDamage = 4;
        setLifepoints(5);
        addMechanics(new Taunt(), new DivineShield());
    }
}
