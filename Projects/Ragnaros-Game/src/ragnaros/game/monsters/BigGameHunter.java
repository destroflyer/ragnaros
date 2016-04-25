/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.filters.*;

/**
 *
 * @author Carl
 */
public class BigGameHunter extends MonsterCard{

    public BigGameHunter(){
        description = new Description("Big Game Hunter");
        flavorText = "\"I've got the fun in my sights!\"";
        manaTypes = new Mana[]{Mana.CUSTOM};
        castCost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
        attackDamage = 4;
        setLifepoints(2);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Destroy a monster with 7 or more attack.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to destroy.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALL, new AttackDamageCardFilter(){

                        @Override
                        protected boolean isValid(float value){
                            return (value >= 7);
                        }
                    });
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new DestroyMonsterEvent(target));
            }
        });
    }
}
