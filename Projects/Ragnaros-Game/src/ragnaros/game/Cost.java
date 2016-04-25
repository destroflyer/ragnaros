/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class Cost{

    public Cost(){
        this(new ManaAmount());
    }

    public Cost(ManaAmount mana){
        this(mana, false);
    }

    public Cost(ManaAmount mana, boolean tap){
        this(mana, tap, 0);
    }

    public Cost(ManaAmount mana, boolean tap, int lifepoints){
        this.mana = mana;
        this.tap = tap;
        this.lifepoints = lifepoints;
    }
    private ManaAmount mana;
    private boolean tap;
    private int lifepoints;
    
    public boolean isEmpty(){
        return (mana.isEmpty() && (lifepoints == 0) && (!tap));
    }

    public ManaAmount getMana(){
        return mana;
    }

    public boolean isTap(){
        return tap;
    }

    public int getLifepoints(){
        return lifepoints;
    }
}
