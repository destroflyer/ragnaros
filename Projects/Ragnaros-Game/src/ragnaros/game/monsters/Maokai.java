/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.buffs.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Maokai extends MonsterCard{

    public Maokai(){
        description = new Description("Maokai", "I'm a tree and when you summon me, gain attack damage and lifepoints equal to the amount of your available green mana.");
        manaTypes = new Mana[]{Mana.GREEN};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 3));
        attackDamage = 0;
        setLifepoints(0);
    }

    @Override
    public void onCast(){
        super.onCast();
        int availableGreenMana = owner.getAvailableMana().getMana(Mana.GREEN);
        game.triggerEvent(new AddBuffEvent(this, new CompositeBuff(new AdditiveAttackDamageBuff(availableGreenMana), new AdditiveMaximumHealthBuff(availableGreenMana)), this));
    }
}
