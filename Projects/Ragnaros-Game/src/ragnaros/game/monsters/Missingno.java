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
public class Missingno extends MonsterCard{

    public Missingno(){
        description = new Description("Missingno.");
        isCollectible = false;
        manaTypes = new Mana[]{Mana.CUSTOM};
        tribes = new Tribe[]{Tribe.POKEMON};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 0));
        attackDamage = 1;
        setLifepoints(1);
    }
}
