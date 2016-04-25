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
public class MurlocTidehunter extends MonsterCard{

    public MurlocTidehunter(){
        description = new Description("Murloc Tidehunter");
        manaTypes = new Mana[]{Mana.GREEN};
        tribes = new Tribe[]{Tribe.MURLOC};
        castCost = new Cost(new ManaAmount(Mana.GREEN, 2));
        attackDamage = 2;
        setLifepoints(1);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Summon a 1/1 Murloc Scout.");
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.triggerEvent(new SummonMonsterEvent(owner, new MurlocScout()));
                //new OggClip("/cardgame/resources/sounds/murloc.ogg").play();
            }
        });
    }
}
