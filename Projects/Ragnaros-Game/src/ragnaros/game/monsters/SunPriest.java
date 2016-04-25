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
public class SunPriest extends MonsterCard{

    public SunPriest(){
        description = new Description("Sun Priest");
        flavorText = "In love with Leona.";
        manaTypes = new Mana[]{Mana.WHITE};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 4));
        attackDamage = 3;
        setLifepoints(6);
    }
}
