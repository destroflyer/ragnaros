/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.game.monsters.spells.parameter.*;

/**
 *
 * @author Carl
 */
public abstract class Spell{

    public Spell(){
        description = new Description(getClass().getSimpleName());
    }
    protected Card caster;
    protected Description description;
    protected Cost cost = new Cost();
    protected SpellParameterFormat spellParameterFormat = new SpellParameterFormat();
    
    public boolean isValidTargetAvailable(){
        for(int i=0;i<spellParameterFormat.size();i++){
            SpellParameterFormat_Entry formatEntry = spellParameterFormat.getEntry(i);
            boolean hasValidTarget = false;
            for(Card card : caster.getGame().getCards()){
                if(formatEntry.isValid(this, card)){
                    hasValidTarget = true;
                    break;
                }
            }
            if(!hasValidTarget){
                return false;
            }
        }
        return true;
    }

    public void setCaster(Card caster){
        this.caster = caster;
    }

    public Card getCaster(){
        return caster;
    }

    public Description getDescription(){
        return description;
    }

    public void setCost(Cost cost){
        this.cost = cost;
    }

    public Cost getCost(){
        return cost;
    }

    public SpellParameterFormat getSpellParameterFormat(){
        return spellParameterFormat;
    }

    public abstract void cast(Game game, SpellParameter[] parameters);
    
    protected Land getParameter_Land(SpellParameter spellParameter){
        LandParameter landParameter = (LandParameter) spellParameter;
        return (Land) caster.getGame().getPlayer(landParameter.getPlayerIndex()).getLands().get(landParameter.getLandCardIndex());
    }
    
    protected MonsterCard getParameter_Monster(SpellParameter spellParameter){
        MonsterParameter monsterParameter = (MonsterParameter) spellParameter;
        return (MonsterCard) caster.getGame().getPlayer(monsterParameter.getPlayerIndex()).getMonsters().get(monsterParameter.getMonsterCardIndex());
    }
    
    protected Player getParameter_Player(SpellParameter spellParameter){
        HandCardParameter handCardParameter = (HandCardParameter) spellParameter;
        return caster.getGame().getPlayer(handCardParameter.getPlayerIndex());
    }
}
