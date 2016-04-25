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
public class AttackPlayerCommand extends Command{

    public AttackPlayerCommand(){
        
    }

    public AttackPlayerCommand(int monsterCardIndex){
        this.monsterCardIndex = monsterCardIndex;
    }
    private int monsterCardIndex;

    public int getMonsterCardIndex(){
        return monsterCardIndex;
    }
}
