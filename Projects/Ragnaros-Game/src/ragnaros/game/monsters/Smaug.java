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
public class Smaug extends MonsterCard{

    public Smaug(){
        description = new Description("Smaug");
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.DRAGON};
        castCost = new Cost(new ManaAmount(Mana.RED, 8));
        attackDamage = 8;
        setLifepoints(8);
    }
}
