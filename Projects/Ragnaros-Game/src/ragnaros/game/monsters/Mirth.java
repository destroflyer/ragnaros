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
public class Mirth extends MonsterCard{

    public Mirth(){
        description = new Description("Mirth");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 2));
        attackDamage = 1;
        setLifepoints(2);
        hasCharge = true;
        addMechanics(new Windfury());
    }
}
