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
public class CrownedCeratok extends MonsterCard{

    public CrownedCeratok(){
        description = new Description("Crowned Ceratok");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 1, 3));
        attackDamage = 4;
        setLifepoints(3);
        addMechanics(new Trample());
    }
}
