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
public class Arbok extends MonsterCard{

    public Arbok(){
        description = new Description("Arbok");
        isCollectible = false;
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 3));
        attackDamage = 4;
        setLifepoints(3);
    }
}
