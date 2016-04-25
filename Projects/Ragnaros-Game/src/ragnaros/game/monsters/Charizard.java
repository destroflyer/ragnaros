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
public class Charizard extends MonsterCard{

    public Charizard(){
        description = new Description("Charizard");
        isCollectible = false;
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.DRAGON};
        castCost = new Cost(new ManaAmount(Mana.RED, 5));
        attackDamage = 6;
        setLifepoints(5);
    }
}
