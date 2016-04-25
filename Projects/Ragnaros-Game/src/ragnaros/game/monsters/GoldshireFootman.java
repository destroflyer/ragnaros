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
public class GoldshireFootman extends MonsterCard{

    public GoldshireFootman(){
        description = new Description("Goldshire Footman");
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 1));
        attackDamage = 1;
        setLifepoints(2);
        addMechanics(new Taunt());
    }
}
