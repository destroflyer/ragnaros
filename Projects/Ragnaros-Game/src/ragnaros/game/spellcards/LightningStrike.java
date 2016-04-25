/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.monsters.spells.parameter.*;

/**
 *
 * @author Carl
 */
public class LightningStrike extends SpellCard{

    public LightningStrike(){
        super(new Spell(){{
                description = new Description("Lightning Strike", "Discard 1 card to destroy all enemy monsters.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the card to discard.", SpellParameterFormat_Entry.Type.HAND_CARD, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                int handCardIndex = ((HandCardParameter) parameters[0]).getHandCardIndex();
                game.triggerEvent(new DiscardEvent(caster.getOwner(), handCardIndex));
                Cards enemyMonsters = caster.getOwner().getEnemy().getMonsters();
                for(int i=0;i<enemyMonsters.size();i++){
                    MonsterCard monsterCard = (MonsterCard) enemyMonsters.get(i);
                    if(game.triggerEvent(new DestroyMonsterEvent(monsterCard))){
                        i--;
                    }
                }
            }
        });
    }
}
