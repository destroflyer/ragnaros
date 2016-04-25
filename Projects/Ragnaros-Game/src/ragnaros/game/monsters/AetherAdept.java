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
public class AetherAdept extends MonsterCard{

    public AetherAdept(){
        description = new Description("Aether Adept");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.SPELLCASTER};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 2, 1));
        attackDamage = 2;
        setLifepoints(2);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Return a monster to its owners hand.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to return.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new ReturnMonsterToHandEvent(target));
            }
        });
    }
}
