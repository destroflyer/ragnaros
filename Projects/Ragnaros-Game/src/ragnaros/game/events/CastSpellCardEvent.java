/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.events;

import ragnaros.game.*;
import ragnaros.game.monsters.spells.parameter.HandCardParameter;

/**
 *
 * @author Carl
 */
public class CastSpellCardEvent extends Event{

    public CastSpellCardEvent(SpellCard spellCard, SpellParameter[] parameters){
        this.spellCard = spellCard;
        this.parameters = parameters;
    }
    protected SpellCard spellCard;
    protected SpellParameter[] parameters;

    @Override
    public void trigger(Game game){
        int spellCardHandCardIndex = spellCard.getOwner().getHand().indexOf(spellCard);
        if(game.triggerEvent(new ThrowOffEvent(spellCard))){
            for(int i=0;i<parameters.length;i++){
                if(parameters[i] instanceof HandCardParameter){
                    HandCardParameter handCardParameter = (HandCardParameter) parameters[i];
                    if(handCardParameter.getHandCardIndex() > spellCardHandCardIndex){
                        parameters[i] = new HandCardParameter(handCardParameter.getPlayerIndex(), handCardParameter.getHandCardIndex() - 1);
                    }
                }
            }
            game.triggerEvent(new CastSpellEvent(spellCard.getSpell(), parameters));
        }
    }

    public SpellCard getSpellCard(){
        return spellCard;
    }

    public SpellParameter[] getParameters(){
        return parameters;
    }
}
