/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network.messages;

/**
 *
 * @author Carl
 */
public class Message_ClientAccepted{

    public Message_ClientAccepted(){
        
    }

    public Message_ClientAccepted(String gameModeClassName, int playerIndex){
        this.gameModeClassName = gameModeClassName;
        this.playerIndex = playerIndex;
    }
    private String gameModeClassName;
    private int playerIndex;

    public String getGameModeClassName(){
        return gameModeClassName;
    }

    public int getPlayerIndex(){
        return playerIndex;
    }
}
