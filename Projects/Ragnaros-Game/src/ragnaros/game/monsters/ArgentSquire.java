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
public class ArgentSquire extends MonsterCard{

    public ArgentSquire(){
        description = new Description("Argent Squire");
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 1;
        setLifepoints(1);
        addMechanics(new DivineShield());
    }
}
