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
public class VedalkenInfuser extends MonsterCard{

    public VedalkenInfuser(){
        description = new Description("Vedalken Infuser");
        manaTypes = new Mana[]{Mana.BLUE};
        castCost = new Cost(new ManaAmount(new int[]{3, 0, 0, 0, 1, 0}));
        attackDamage = 2;
        setLifepoints(4);
        setCastSpell(new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Give an allied monster Charge.");
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to give Charge.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                target.setHasCharge(true);
            }
        });
    }
}
