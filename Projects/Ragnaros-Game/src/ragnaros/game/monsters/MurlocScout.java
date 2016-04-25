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
public class MurlocScout extends MonsterCard{

    public MurlocScout(){
        description = new Description("Murloc Scout");
        flavorText = "\"Blargagrhagh!\"";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.MURLOC};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 1));
        attackDamage = 1;
        setLifepoints(1);
    }
}
