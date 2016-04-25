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
public class SavannahHighmane extends MonsterCard{

    public SavannahHighmane(){
        description = new Description("Savannah Highmane", "When this monster dies, summon two 2/2 hyenas.");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.BEAST};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 6));
        attackDamage = 6;
        setLifepoints(5);
    }

    @Override
    public void onDestroyed(){
        super.onDestroyed();
        for(int i=0;i<2;i++){
            Hyena hyena = new Hyena();
            if(game.triggerEvent(new SummonMonsterEvent(owner, hyena))){
                game.triggerEvent(new TapEvent(hyena));
            }
        }
    }
}
