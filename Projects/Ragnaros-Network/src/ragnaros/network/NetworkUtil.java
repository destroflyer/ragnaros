/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 *
 * @author Carl
 */
public class NetworkUtil{
    
    public static boolean isPortAvailable(int port){
        ServerSocket serverSocket = null;
        DatagramSocket datagramSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            datagramSocket = new DatagramSocket(port);
            datagramSocket.setReuseAddress(true);
            return true;
        }catch(Exception ex){
        }finally{
            if(datagramSocket != null){
                datagramSocket.close();
            }
            if(serverSocket != null){
                try{
                    serverSocket.close();
                }catch(Exception ex){
                }
            }
        }
        return false;
    }

    public static byte[] serialize(BitSerializable bitSerializable){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(byteArrayOutputStream);
        bitSerializable.write(bitOutputStream);
        bitOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void unserialize(BitSerializable bitSerializable, byte[] data){
        BitInputStream bitInputStream = new BitInputStream(new ByteArrayInputStream(data));
        try{
            bitSerializable.read(bitInputStream);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
