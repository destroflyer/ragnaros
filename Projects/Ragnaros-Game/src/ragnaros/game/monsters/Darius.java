/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class Darius extends MonsterCard{

    public Darius(){
        description = new Description("Darius");
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(Mana.RED, 2));
        attackDamage = 4;
        setLifepoints(2);
    }
}
