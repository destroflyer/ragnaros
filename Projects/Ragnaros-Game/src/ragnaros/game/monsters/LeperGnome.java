/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class LeperGnome extends MonsterCard{

    public LeperGnome(){
        description = new Description("Leper Gnome", "When this monster dies, deal 2 damage to the enemy player.");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 1));
        attackDamage = 2;
        setLifepoints(1);
    }

    @Override
    public void onDestroyed(){
        super.onDestroyed();
        game.triggerEvent(new AddPlayerLifepointsEvent(owner.getEnemy(), -2));
    }
}
