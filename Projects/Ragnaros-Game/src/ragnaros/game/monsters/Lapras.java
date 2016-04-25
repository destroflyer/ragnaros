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
public class Lapras extends MonsterCard{

    public Lapras(){
        description = new Description("Lapras");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.POKEMON, Tribe.FISH};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 2));
        attackDamage = 2;
        setLifepoints(3);
    }
}
