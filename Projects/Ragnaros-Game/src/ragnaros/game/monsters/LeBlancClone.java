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
public class LeBlancClone extends MonsterCard{

    public LeBlancClone(){
        description = new Description("LeBlanc (Clone)");
        flavorText = "\"I'm the real one!!!111\"";
        isCollectible = false;
        manaTypes = new Mana[]{Mana.WHITE};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.WHITE, 3));
        attackDamage = 0;
        setLifepoints(5);
    }
}
