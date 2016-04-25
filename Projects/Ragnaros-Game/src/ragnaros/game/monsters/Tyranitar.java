/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class Tyranitar extends MonsterCard{

    public Tyranitar(){
        description = new Description("Tyranitar");
        manaTypes = new Mana[]{Mana.BLACK};
        tribes = new Tribe[]{Tribe.POKEMON};
        castCost = new Cost(new ManaAmount(Mana.BLACK, 2, 4));
        attackDamage = 6;
        setLifepoints(5);
        setSpells(new Spell[]{new Spell(){{
                description = new Description(Description.DEFAULT_TITLE, "Give an allied monster Trample and Immune until the end of turn. It can't be untapped in your 3rd and 4th round after the activation of this effect.");
                cost = new Cost(new ManaAmount(Mana.BLACK, 3));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the monster to buff.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                final MonsterCard target = getParameter_Monster(parameters[0]);
                game.triggerEvent(new AddMechanicEvent(target, new Trample(), caster));
                game.addEventListener(new EventListener(){
                    
                    private int turnNumber;

                    @Override
                    public boolean preEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof TurnStartEvent){
                            TurnStartEvent event = (TurnStartEvent) receivedEvent;
                            if(event.getPlayer() != target.getOwner()){
                                turnNumber++;
                                if(turnNumber > 4){
                                    game.removeEventListener(this);
                                }
                            }
                        }
                        else if(receivedEvent instanceof AttackEvent){
                            AttackEvent event = (AttackEvent) receivedEvent;
                            if(event.getAttackingMonsterCard() == target){
                                game.triggerEvent(new AddMechanicEvent(target, new Immune(), caster));
                            }
                        }
                        else if(receivedEvent instanceof UntapEvent){
                            UntapEvent event = (UntapEvent) receivedEvent;
                            if((event.getFieldCard() == target) && (turnNumber > 2)){
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override
                    public void postEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof AttackEvent){
                            AttackEvent event = (AttackEvent) receivedEvent;
                            if(event.getAttackingMonsterCard() == target){
                                game.triggerEvent(new RemoveMechanicEvent(target, Immune.class));
                            }
                        }
                    }
                });
            }
        }});
    }
}
