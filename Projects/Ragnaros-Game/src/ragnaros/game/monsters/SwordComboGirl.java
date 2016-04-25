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
public class SwordComboGirl extends MonsterCard{

    public SwordComboGirl(){
        description = new Description("Sword Combo Girl");
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 2;
        setLifepoints(1);
        addMechanics(new Windfury());
    }
}
