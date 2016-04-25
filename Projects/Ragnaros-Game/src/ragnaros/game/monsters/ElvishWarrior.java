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
public class ElvishWarrior extends MonsterCard{

    public ElvishWarrior(){
        description = new Description("Elvish Warrior");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.ELF};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 2;
        setLifepoints(3);
    }
}
