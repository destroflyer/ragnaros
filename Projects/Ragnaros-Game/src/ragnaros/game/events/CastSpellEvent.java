/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.events;

import ragnaros.game.*;

/**
 *
 * @author Carl
 */
public class CastSpellEvent extends Event{

    public CastSpellEvent(Spell spell, SpellParameter[] parameters){
        this.spell = spell;
        this.parameters = parameters;
    }
    protected Spell spell;
    protected SpellParameter[] parameters;

    @Override
    public void trigger(Game game){
        spell.cast(game, parameters);
    }

    public Spell getSpell(){
        return spell;
    }

    public SpellParameter[] getParameters(){
        return parameters;
    }
}
