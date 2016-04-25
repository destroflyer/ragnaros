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
public class Aaah extends SpellCard{

    public Aaah(){
        super(new Spell(){{
                description = new Description("Aaah!", "Until the end of this turn, all pokemons flee from mortal damage and therefore can't die.");
                cost = new Cost(new ManaAmount(Mana.CUSTOM, 3));
            }
                

            @Override
            public void cast(Game game, SpellParameter[] parameters){
                game.addEventListener(new EventListener(){

                    @Override
                    public boolean preEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof DestroyMonsterEvent){
                            DestroyMonsterEvent event = (DestroyMonsterEvent) receivedEvent;
                            if(event.getMonsterCard().hasTribe(MonsterCard.Tribe.POKEMON)){
                                event.getMonsterCard().setCurrentLifepoints(1);
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override
                    public void postEvent(Game game, Event receivedEvent){
                        if(receivedEvent instanceof TurnEndEvent){
                            game.removeEventListener(this);
                        }
                    }
                });
            }
        });
        flavorText = "\"Hide yo kids!\"";
    }
}
