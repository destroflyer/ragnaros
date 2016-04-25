/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.spells.*;

/**
 *
 * @author Carl
 */
public class Tuska extends MonsterCard{

    public Tuska(){
        description = new Description("Tuska");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 1));
        attackDamage = 1;
        setLifepoints(1);
        setSpells(new TabForManaSpell(new ManaAmount(Mana.GREEN, 1)));
    }
}
