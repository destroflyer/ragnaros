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
public class Brand extends MonsterCard{

    public Brand(){
        description = new Description("Brand");
        flavorText = "Cause this is Thriller, Thriller night!";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.PYRO};
        castCost = new Cost(new ManaAmount(Mana.RED, 1));
        attackDamage = 1;
        setLifepoints(1);
        hasCharge = true;
    }
}
