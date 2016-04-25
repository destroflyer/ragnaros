/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class Deck extends ResettedCards{

    public Deck(){
        super(CardPosition.Zone.DECK);
    }
    private Description description = new Description();

    public void setDescription(Description description){
        this.description = description;
    }

    public Description getDescription(){
        return description;
    }
}
