/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import ragnaros.game.*;
import ragnaros.game.events.*;
import ragnaros.network.*;
import ragnaros.network.messages.*;
import ragnaros.network.messages.objects.*;
import com.esotericsoftware.kryonet.*;

/**
 *
 * @author Carl
 */
public class MasterServer{
    
    public MasterServer(int port){
        this.port = port;
        server = new Server(MessageSerializer.BUFFER_SIZE_WRITE, MessageSerializer.BUFFER_SIZE_OBJECT);
        MessageSerializer.registerClasses(server.getKryo());
        server.addListener(new Listener(){

            @Override
            public void received(final Connection connection, Object receivedMessage){
                if(receivedMessage instanceof Message_GetUpdateFiles){
                    connection.sendTCP(new Message_UpdateFiles(updateManager.getUpdateFiles()));
                }
                else if(receivedMessage instanceof Message_GetUpdateFile){
                    Message_GetUpdateFile message = (Message_GetUpdateFile) receivedMessage;
                    if((message.getIndex() >= 0) && (message.getIndex() < updateManager.getUpdateFiles().length)){
                        UpdateFile updateFile = updateManager.getUpdateFiles()[message.getIndex()];
                        try{
                            String filePath = (updateManager.getUpdateFilesDirectory() + updateFile.getFilePath().substring(2));
                            FileInputStream fileInputStream = new FileInputStream(filePath);
                            byte[] buffer = new byte[MessageSerializer.FILE_PART_SIZE];
                            int readBytes = 0;
                            while((readBytes = fileInputStream.read(buffer)) != -1){
                                byte[] data = Arrays.copyOf(buffer, readBytes);
                                connection.sendTCP(new Message_UpdateFilePart(data));
                            }
                            fileInputStream.close();
                        }catch(FileNotFoundException ex){
                            ex.printStackTrace();
                        }catch(IOException ex){
                            ex.printStackTrace();
                        }
                    }
                }
                else if(receivedMessage instanceof Message_ClientInitialized){
                    Message_ClientInitialized message = (Message_ClientInitialized) receivedMessage;
                }
                else if(receivedMessage instanceof Message_EnterGameQueue){
                    Message_EnterGameQueue message = (Message_EnterGameQueue) receivedMessage;
                    final RunningGame runningGame = gameQueue.addPlayer(connection.getID(), message.getDeckData());
                    if(runningGame != null){
                        runningGame.getGame().addEventListener(new EventListener(){
                            
                            @Override
                            public boolean preEvent(Game game, Event receivedEvent){
                                return true;
                            }

                            @Override
                            public void postEvent(Game game, Event receivedEvent){
                                if(receivedEvent instanceof GameOverEvent){
                                    GameOverEvent event = (GameOverEvent) receivedEvent;
                                    for(GameQueuePlayer queuePlayer : runningGame.getQueuePlayers()){
                                        runningGames.remove(queuePlayer.getConnectionID());
                                    }
                                    runningGame.sendToTCP(new Message_GameOver(event.getWinner().getIndex()));
                                }
                            }
                        });
                        for(GameQueuePlayer queuePlayer : runningGame.getQueuePlayers()){
                            runningGames.put(queuePlayer.getConnectionID(), runningGame);
                        }
                        runningGame.getGame().startGame();
                        runningGame.sendGameUpdate();
                    }
                }
                else if(receivedMessage instanceof Message_Command){
                    Message_Command message = (Message_Command) receivedMessage;
                    RunningGame runningGame = runningGames.get(connection.getID());
                    if(runningGame != null){
                        Game game = runningGame.getGame();
                        if(game.isRunning()){
                            game.executeCommand(message.getCommand());
                            runningGame.sendGameUpdate();
                        }
                    }
                }
            }
        });
        gameQueue = new GameQueue(server);
        try{
            server.bind(port, port + 1);
            server.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private int port;
    private Server server;
    private UpdateManager updateManager = new UpdateManager();
    private GameQueue gameQueue;
    private HashMap<Integer, RunningGame> runningGames = new HashMap<Integer, RunningGame>();

    public int getPort(){
        return port;
    }
    
    public static int generatePort_Free(){
        int port = 33899;
        do{
            port++;
        }while(!NetworkUtil.isPortAvailable(port));
        return port;
    }
    
    public static int generatePort_Random(){
        return (33900 + ((int) (Math.random() * 100)));
    }
}
