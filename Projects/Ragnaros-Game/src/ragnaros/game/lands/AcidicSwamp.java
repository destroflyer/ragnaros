/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.lands;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;
import ragnaros.game.spells.*;

/**
 *
 * @author Carl
 */
public class AcidicSwamp extends Land{

    public AcidicSwamp(){
        description = new Description("Acidic Swamp");
        manaTypes = new Mana[]{Mana.BLACK};
        castCost = new Cost(new ManaAmount(), true);
        setSpells(
            new TabForManaSpell(new ManaAmount(Mana.BLACK, 1)),
            new Spell(){{
                    description = new Description("Destroy Monster", "Destroy an enemy monster with 1 or less attack.");
                    cost = new Cost(new ManaAmount(), true);
                    spellParameterFormat = new SpellParameterFormat(){{
                        add("Choose the monster to destroy.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ENEMY, new AttackDamageCardFilter(){

                            @Override
                            protected boolean isValid(float value){
                                return (value <= 1);
                            }
                        });
                    }};
                }

                @Override
                public void cast(Game game, SpellParameter[] parameters){
                    MonsterCard target = getParameter_Monster(parameters[0]);
                    game.triggerEvent(new DestroyMonsterEvent(target));
                }
            }
        );
    }
}
