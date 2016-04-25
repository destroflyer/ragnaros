/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.commands;

import ragnaros.game.Command;

/**
 *
 * @author Carl
 */
public class AttackMonsterCommand extends Command{

    public AttackMonsterCommand(){
        
    }

    public AttackMonsterCommand(int monsterCardIndex, int targetMonsterCardIndex){
        this.monsterCardIndex = monsterCardIndex;
        this.targetMonsterCardIndex = targetMonsterCardIndex;
    }
    private int monsterCardIndex;
    private int targetMonsterCardIndex;

    public int getMonsterCardIndex(){
        return monsterCardIndex;
    }

    public int getTargetMonsterCardIndex(){
        return targetMonsterCardIndex;
    }
}
