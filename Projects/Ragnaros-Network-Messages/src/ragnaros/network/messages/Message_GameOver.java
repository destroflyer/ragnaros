/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

/**
 *
 * @author Carl
 */
public class Message_GameOver{
    
    public Message_GameOver(){
        
    }
    
    public Message_GameOver(int winnerPlayerIndex){
        this.winnerPlayerIndex = winnerPlayerIndex;
    }
    private int winnerPlayerIndex;

    public int getWinnerPlayerIndex(){
        return winnerPlayerIndex;
    }
}
