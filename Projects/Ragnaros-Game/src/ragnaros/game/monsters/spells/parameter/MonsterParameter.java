/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.monsters.spells.parameter;

import ragnaros.game.SpellParameter;

/**
 *
 * @author Carl
 */
public class MonsterParameter extends SpellParameter{

    public MonsterParameter(){
        
    }

    public MonsterParameter(int playerIndex, int monsterCardIndex){
        this.playerIndex = playerIndex;
        this.monsterCardIndex = monsterCardIndex;
    }
    private int playerIndex;
    private int monsterCardIndex;

    public int getPlayerIndex(){
        return playerIndex;
    }

    public int getMonsterCardIndex(){
        return monsterCardIndex;
    }
}
