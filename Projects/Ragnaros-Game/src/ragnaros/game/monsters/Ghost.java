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
public class Ghost extends MonsterCard{

    public Ghost(){
        description = new Description("Ghost");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 2));
        attackDamage = 0;
        setLifepoints(1);
        addMechanics(new Immune());
    }
}
