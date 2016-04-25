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
public class Nami extends MonsterCard{

    public Nami(){
        description = new Description("Nami");
        manaTypes = new Mana[]{Mana.BLUE};
        tribes = new Tribe[]{Tribe.FISH};
        castCost = new Cost(new ManaAmount(Mana.BLUE, 1));
        attackDamage = 1;
        setLifepoints(1);
        setSpells(new Spell[]{new Spell(){{
                description = new Description("Tidecaller's Blessing", "Select a monster - Its attacks deal 1 extra damage until the end of this turn. (Has only one impact per turn)");
                cost = new Cost(new ManaAmount(Mana.BLUE, 1));
                spellParameterFormat = new SpellParameterFormat(){{
                    add("Choose the target to buff.", SpellParameterFormat_Entry.Type.MONSTER, SpellParameterFormat_Entry.Owner.ALLIED);
                }};
            }

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                if(!hasBuffedThisTurn){
                    final MonsterCard target = getParameter_Monster(parameters[0]);
                    game.addEventListener(new EventListener(){

                        @Override
                        public boolean preEvent(Game game, Event receivedEvent){
                            return true;
                        }

                        @Override
                        public void postEvent(Game game, Event receivedEvent){
                            if(receivedEvent instanceof AttackMonsterEvent){
                                AttackMonsterEvent event = (AttackMonsterEvent) receivedEvent;
                                if(event.getAttackingMonsterCard() == target){
                                    game.triggerEvent(new AddMonsterCurrentLifepointsEvent(event.getTargetMonsterCard(), -1));
                                }
                            }
                            else if(receivedEvent instanceof AttackPlayerEvent){
                                AttackPlayerEvent event = (AttackPlayerEvent) receivedEvent;
                                if(event.getAttackingMonsterCard() == target){
                                    game.triggerEvent(new AddPlayerLifepointsEvent(event.getTargetPlayer(), -1));
                                }
                            }
                            else if(receivedEvent instanceof TurnEndEvent){
                                game.removeEventListener(this);
                                hasBuffedThisTurn = false;
                            }
                        }
                    });
                    hasBuffedThisTurn = true;
                }
            }
        }});
    }
    private boolean hasBuffedThisTurn;
}
