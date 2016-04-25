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
public class Tauros extends MonsterCard{

    public Tauros(){
        description = new Description("Tauros");
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 2));
        attackDamage = 3;
        setLifepoints(2);
    }
}
