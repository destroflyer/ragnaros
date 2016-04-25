/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client.updater;

import ragnaros.network.messages.objects.UpdateFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import ragnaros.network.messages.Message_UpdateFilePart;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 *
 * @author Carl
 */
public class WriteUpdateFileListener extends Listener{

    public WriteUpdateFileListener(UpdateFile updateFile){
        this.updateFile = updateFile;
        try{
            fileOutputStream = new FileOutputStream(updateFile.getFilePath());
        }catch(FileNotFoundException ex){
            System.out.println("Error while initializing download '" + updateFile.getFilePath() + "'.");
        }
    }
    private UpdateFile updateFile;
    private FileOutputStream fileOutputStream;
    private int writtenBytes;

    @Override
    public void received(Connection connection, Object receivedMessage){
        super.received(connection, receivedMessage);
        if(receivedMessage instanceof Message_UpdateFilePart){
            Message_UpdateFilePart message = (Message_UpdateFilePart) receivedMessage;
            try{
                fileOutputStream.write(message.getData());
                writtenBytes += message.getData().length;
                if(isFinished()){
                    fileOutputStream.close();
                }
            }catch(IOException ex){
                System.out.println("Error while downloading file '" + updateFile.getFilePath() + "'.");
            }
        }
    }
    
    public boolean isFinished(){
        return (writtenBytes == updateFile.getSize());
    }
}
