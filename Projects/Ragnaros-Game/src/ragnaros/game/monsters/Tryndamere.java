/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters;

import ragnaros.game.*;
import ragnaros.game.events.*;

/**
 *
 * @author Carl
 */
public class Tryndamere extends MonsterCard{

    public Tryndamere(){
        description = new Description("Tryndamere");
        flavorText = "Chop chop.";
        manaTypes = new Mana[]{Mana.RED};
        tribes = new Tribe[]{Tribe.WARRIOR};
        castCost = new Cost(new ManaAmount(new int[]{0, 0, 2, 0, 0, 0}));
        attackDamage = 4;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Undying Rage", "This cards lifepoints can't fall to 0 for the next 3 turns.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 4));
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.addEventListener(new EventListener(){
                    
                    private int remainingTurns = 3;

                    @Override
                    public boolean preEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof DestroyMonsterEvent){
                            DestroyMonsterEvent event = (DestroyMonsterEvent) receivedEvent;
                            if(event.getMonsterCard() == caster){
                                event.getMonsterCard().setCurrentLifepoints(1);
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override
                    public void postEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof TurnEndEvent){
                            remainingTurns--;
                            if(remainingTurns <= 0){
                                game.removeEventListener(this);
                            }
                        }
                    }
                });
            }
        }});
    }
}
