/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import ragnaros.network.BitSerializable;
import ragnaros.network.BitOutputStream;
import ragnaros.network.BitInputStream;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Carl
 */
public class ManaAmount implements BitSerializable{

    public ManaAmount(){
        
    }

    public ManaAmount(int[] mana){
        this.mana = mana;
    }

    public ManaAmount(Mana mana, int amount){
        this(mana, amount, 0);
    }

    public ManaAmount(Mana mana, int amount, int customAmount){
        this.mana[0] = customAmount;
        this.mana[getIndex(mana)] = amount;
    }
    private int[] mana = new int[Mana.values().length];
    
    public void clear(){
        for(int i=0;i<mana.length;i++){
            mana[i] = 0;
        }
    }
    
    public void set(ManaAmount manaAmount){
        for(int i=0;i<mana.length;i++){
            mana[i] = manaAmount.mana[i];
        }
    }
    
    public void add(ManaAmount manaAmount){
        for(int i=0;i<mana.length;i++){
            mana[i] += manaAmount.mana[i];
        }
    }
    
    public void add(Mana mana, int amount){
        this.mana[getIndex(mana)] += amount;
    }
    
    public void subtractFromAvailable(ManaAmount manaAmount){
        for(int i=1;i<mana.length;i++){
            mana[i] -= manaAmount.mana[i];
            if(mana[i] < 0){
                mana[i] = 0;
            }
        }
        int remainingAmount = manaAmount.getMana(Mana.CUSTOM);
        for(int i=0;i<mana.length;i++){
            if(mana[i] >= remainingAmount){
                mana[i] -= remainingAmount;
                break;
            }
            else{
                mana[i] = 0;
                remainingAmount -= mana[i];
            }
        }
    }
    
    public void subtractFromRemaining(ManaAmount manaAmount){
        for(int i=0;i<mana.length;i++){
            int newMana = (mana[i] - manaAmount.mana[i]);
            if(newMana < 0){
                mana[0] += newMana;
                mana[i] = 0;
            }
            else{
                mana[i] = newMana;
            }
        }
    }
    
    public int getMana(Mana mana){
        return this.mana[getIndex(mana)];
    }
    
    public int getAmount(){
        int amount = 0;
        for(int i=0;i<mana.length;i++){
            amount += mana[i];
        }
        return amount;
    }

    public boolean canBeUsedToPay(ManaAmount manaAmount){
        return (contains(manaAmount) || manaAmount.canBePartlyFilledWith(this));
    }

    public boolean contains(ManaAmount manaAmount){
        int remainingMana = mana[0];
        for(int i=1;i<mana.length;i++){
            int difference = (mana[i] - manaAmount.mana[i]);
            if(difference < 0){
                return false;
            }
            remainingMana += difference;
        }
        return (remainingMana >= manaAmount.mana[0]);
    }

    public boolean canBePartlyFilledWith(ManaAmount manaAmount){
        int remainingMana = manaAmount.mana[0];
        for(int i=1;i<mana.length;i++){
            int difference = (manaAmount.mana[i] - mana[i]);
            if(difference > 0){
                remainingMana += difference;
            }
        }
        return (remainingMana <= mana[0]);
    }
    
    public Mana[] getUsedManaTypes(){
        return getUsedManaTypes(true);
    }
    
    public Mana[] getUsedManaTypes(boolean includeCustom){
        LinkedList<Mana> manaTypes = new LinkedList<Mana>();
        for(int i=(includeCustom?0:1);i<mana.length;i++){
            if(mana[i] > 0){
                manaTypes.add(Mana.values()[i]);
            }
        }
        return manaTypes.toArray(new Mana[manaTypes.size()]);
    }
    
    public boolean isEmpty(){
        for(int i=0;i<mana.length;i++){
            if(mana[i] > 0){
                return false;
            }
        }
        return true;
    }
    
    public ManaAmount getNeededAmount(ManaAmount availableMana){
        int[] neededMana = new int[mana.length];
        int availableCustomMana = availableMana.getMana(Mana.CUSTOM);
        for(int i=1;i<neededMana.length;i++){
            int needed = (mana[i] - availableMana.mana[i]);
            if(needed > 0){
                neededMana[i] = needed;
            }
            else{
                availableCustomMana -= needed;
            }
        }
        int needed = (mana[0] - availableCustomMana);
        if(needed > 0){
            neededMana[0] = needed;
        }
        return new ManaAmount(neededMana);
    }
    
    private static Color[] manaColors = new Color[]{
        new Color(190, 190, 190),
        new Color(255, 255, 255),
        new Color(255, 50, 0),
        new Color(0, 190, 0),
        new Color(0, 150, 255),
        new Color(30, 30, 30)
    };
    public static Color getColor(Mana mana){
        return manaColors[getIndex(mana)];
    }
    
    public static int getIndex(Mana mana){
        return mana.ordinal();
    }

    @Override
    public void write(BitOutputStream outputStream){
        for(int i=0;i<mana.length;i++){
            outputStream.writeBits(mana[i], 6);
        }
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException{
        for(int i=0;i<mana.length;i++){
            mana[i] = inputStream.readBits(6);
        }
    }

    @Override
    public String toString(){
        String text = "[";
        for(int i=0;i<mana.length;i++){
            if(i != 0){
                text += ", ";
            }
            text += mana[i];
        }
        text += "]";
        return text;
    }
    
    @Override
    public ManaAmount clone(){
        return new ManaAmount(Arrays.copyOf(mana, mana.length));
    }
}
