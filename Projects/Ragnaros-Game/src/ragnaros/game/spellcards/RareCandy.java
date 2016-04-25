/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.monsters.*;

/**
 *
 * @author Carl
 */
public class RareCandy extends SpellCard{

    public RareCandy(){
        super(new Spell(){{
                description = new Description("Rare Candy", "Evolve an allied monster. (WARNING: Was never tested with non-pokemon!)");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to evolve.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                MonsterCard target = getParameter_Monster(parameters[0]);
                MonsterCard evolvedMonster;
                if(target instanceof Charmander){
                    evolvedMonster = new Charmeleon();
                }
                else if(target instanceof Charmeleon){
                    evolvedMonster = new Charizard();
                }
                else if(target instanceof Magikarp){
                    evolvedMonster = new Gyarados();
                }
                else if(target instanceof Ekans){
                    evolvedMonster = new Arbok();
                }
                else{
                    evolvedMonster = new Missingno();
                }
                if(game.triggerEvent(new DestroyMonsterEvent(target))){
                    game.triggerEvent(new SummonMonsterEvent(caster.getOwner(), evolvedMonster));
                }
            }
        });
    }
}
