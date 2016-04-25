/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.network.BitOutputStream;
import ragnaros.network.BitInputStream;
import java.io.IOException;
import ragnaros.game.mechanics.*;

/**
 *
 * @author Carl
 */
public class MonsterCard extends FieldCard{

    public MonsterCard(){
        
    }
    public enum Tribe{
        ANGEL,
        BEAST,
        BUG,
        DRAGON,
        ELF,
        FISH,
        GOBLIN,
        MURLOC,
        OGRE,
        POKEMON,
        PYRO,
        SPELLCASTER,
        WALL,
        WARRIOR,
        ZOMBIE
    }
    protected Tribe[] tribes = new Tribe[0];
    protected int attackDamage;
    protected int maximumLifepoints;
    protected int currentLifepoints;
    protected boolean hasCharge;
    protected Cost attackCost = new Cost(new ManaAmount(), true);
    private boolean isExhausted;
    
    public boolean hasTribe(Tribe tribe){
        for(Tribe currentTribe : tribes){
            if(currentTribe == tribe){
                return true;
            }
        }
        return false;
    }

    public Tribe[] getTribes(){
        return tribes;
    }

    public void setAttackDamage(int attackDamage){
        if(attackDamage < 0){
            attackDamage = 0;
        }
        this.attackDamage = attackDamage;
    }

    public int getAttackDamage(){
        return attackDamage;
    }
    
    public void setLifepoints(int lifepoints){
        maximumLifepoints = lifepoints;
        currentLifepoints = lifepoints;
    }

    public int getMaximumLifepoints(){
        return maximumLifepoints;
    }

    public void setMaximumLifepoints(int maximumLifepoints){
        int difference = (maximumLifepoints - this.maximumLifepoints);
        this.maximumLifepoints = maximumLifepoints;
        if(difference > 0){
            currentLifepoints += difference;
        }
        else if(currentLifepoints > maximumLifepoints){
            currentLifepoints = maximumLifepoints;
        }
    }

    public void setCurrentLifepoints(int currentLifepoints){
        if(currentLifepoints < 0){
            currentLifepoints = 0;
        }
        else if(currentLifepoints > maximumLifepoints){
            currentLifepoints = maximumLifepoints;
        }
        this.currentLifepoints = currentLifepoints;
    }

    public int getCurrentLifepoints(){
        return currentLifepoints;
    }

    public void setHasCharge(boolean hasCharge){
        this.hasCharge = hasCharge;
    }

    public boolean hasCharge(){
        return hasCharge;
    }

    public void setIsExhausted(boolean isExhausted){
        this.isExhausted = isExhausted;
    }

    public boolean isExhausted(){
        return isExhausted;
    }
    
    public boolean canAttack(MonsterCard targetMonsterCard){
        if(!targetMonsterCard.hasMechanic(Taunt.class)){
            Cards targetMonsters = targetMonsterCard.getOwner().getMonsters();
            for(int i=0;i<targetMonsters.size();i++){
                Card otherMonsterCard = targetMonsters.get(i);
                if(otherMonsterCard.hasMechanic(Taunt.class)){
                    return false;
                }
            }
        }
        return (canAttack());
    }
    
    public boolean canAttack(){
        return ((!isExhausted) && owner.canPay(attackCost, this));
    }

    public Cost getAttackCost(){
        return attackCost;
    }

    @Override
    public void write(BitOutputStream outputStream){
        super.write(outputStream);
        outputStream.writeBits(maximumLifepoints, 8);
        outputStream.writeBits(currentLifepoints, 8);
        outputStream.writeBits(attackDamage, 7);
        outputStream.writeBoolean(hasCharge);
        outputStream.writeBoolean(isExhausted);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        super.read(inputStream);
        maximumLifepoints = inputStream.readBits(8);
        currentLifepoints = inputStream.readBits(8);
        attackDamage = inputStream.readBits(7);
        hasCharge = inputStream.readBoolean();
        isExhausted = inputStream.readBoolean();
    }
}
