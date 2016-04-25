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
public class ShieldedMinibot extends MonsterCard{

    public ShieldedMinibot(){
        description = new Description("Shielded Minibot");
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 2));
        attackDamage = 2;
        setLifepoints(2);
        addMechanics(new DivineShield());
    }
}
