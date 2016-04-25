/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.spellcards;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class SwordsOfRevealingLight extends SpellCard{

    public SwordsOfRevealingLight(){
        super(new Spell(){{
                description = new Description("Swords of Revealing Light", "Your enemies attacks won't have any effect during his next turn.");
                cost = new Cost(new ManaAmount(Mana.WHITE, 3));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.addEventListener(new EventListener(){

                    @Override
                    public boolean preEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof AttackEvent){
                            AttackEvent event = (AttackEvent) receivedEvent;
                            if(event.getAttackingMonsterCard().getOwner() != caster.getOwner()){
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override
                    public void postEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof TurnEndEvent){
                            TurnEndEvent event = (TurnEndEvent) receivedEvent;
                            if(event.getPlayer() != caster.getOwner()){
                                game.removeEventListener(this);
                            }
                        }
                    }
                });
            }
        });
    }
}
