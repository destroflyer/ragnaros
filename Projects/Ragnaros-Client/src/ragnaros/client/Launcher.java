/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import java.io.File;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import ragnaros.core.*;
import ragnaros.core.files.FileManager;
import ragnaros.client.updater.*;
import ragnaros.network.MessageSerializer;
import ragnaros.network.messages.*;
import ragnaros.network.messages.objects.*;
import com.esotericsoftware.kryonet.*;

/**
 *
 * @author Carl
 */
public class Launcher extends javax.swing.JFrame{

    public Launcher(){
        initComponents();
        FrameUtil.initWindowSpecials(this);
        FrameUtil.centerFrame(this);
        Toolkit.getDefaultToolkit().addAWTEventListener(keyListener, AWTEvent.KEY_EVENT_MASK);
        setTitle(GameInfo.NAME + " - Launcher");
    }
    private boolean isUpToDate;
    private boolean wasUpdateNeeded;
    private Client client;
    private AWTEventListener keyListener = new AWTEventListener(){

        @Override
        public void eventDispatched(AWTEvent event){
            KeyEvent keyEvent = (KeyEvent) event;
            if(keyEvent.getID() == KeyEvent.KEY_RELEASED){
                switch(keyEvent.getKeyCode()){
                    case KeyEvent.VK_NUMPAD1:
                        createClient();
                        wasUpdateNeeded = false;
                        checkPostUpdateState();
                        break;
                }
            }
        }
    };
    
    private void checkForUpdates(){
        createClient();
        client.addListener(new Listener(){

            @Override
            public void received(Connection connection, Object receivedMessage){
                if(receivedMessage instanceof Message_UpdateFiles){
                    Message_UpdateFiles message = (Message_UpdateFiles) receivedMessage;
                    update(message.getUpdateFiles());
                }
            }
        });
        client.sendTCP(new Message_GetUpdateFiles());
    }
    
    private void createClient(){
        String server = txtServer.getText();
        int port = Integer.parseInt(txtPort.getText());
        client = connect(server, port);
    }
    
    public static Client connect(String server, int port){
        Client client = new Client(MessageSerializer.BUFFER_SIZE_WRITE, MessageSerializer.BUFFER_SIZE_OBJECT);
        MessageSerializer.registerClasses(client.getKryo());
        client.start();
        try{
            client.connect(5000, server, port, port + 1);
        }catch(Exception ex){
            ex.printStackTrace();
            FrameUtil.showMessageDialog(null, "Error while connecting to the server.", FrameUtil.MessageType.ERROR);
        }
        return client;
    }
    
    private void update(final UpdateFile[] updateFiles){
        new Thread(new Runnable(){

            @Override
            public void run(){
                btnAction.setEnabled(false);
                for(int i=0;i<updateFiles.length;i++){
                    UpdateFile updateFile = updateFiles[i];
                    btnAction.setText("Updating " + updateFile.getFilePath());
                    updateFile(updateFiles, i);
                }
                checkPostUpdateState();
            }
        }).start();
    }
    
    private void updateFile(UpdateFile[] updateFiles, int index){
        UpdateFile updateFile = updateFiles[index];
        File file = new File(updateFile.getFilePath());
        if(FileManager.isDirectory(file)){
            FileManager.createDirectoryIfNotExists(file.getPath());
        }
        else{
            boolean needsUpdate = true;
            if(file.exists()){
                String checksumLocalFile = VersionManager.getInstance().getFileChecksumMD5(updateFile.getFilePath());
                needsUpdate = (!checksumLocalFile.equals(updateFile.getChecksum_MD5()));
            }
            if(needsUpdate){
                WriteUpdateFileListener writeUpdateFileListener = new WriteUpdateFileListener(updateFile);
                client.addListener(writeUpdateFileListener);
                client.sendTCP(new Message_GetUpdateFile(index));
                while(!writeUpdateFileListener.isFinished()){
                    try{
                        Thread.sleep(20);
                    }catch(InterruptedException ex){
                    }
                }
                client.removeListener(writeUpdateFileListener);
                VersionManager.getInstance().onFileUpdated(updateFile.getFilePath());
                wasUpdateNeeded = true;
            }
        }
    }
    
    private void checkPostUpdateState(){
        btnAction.setText(wasUpdateNeeded?"Apply Update":"Start");
        btnAction.setEnabled(true);
        isUpToDate = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblServer = new javax.swing.JLabel();
        txtServer = new javax.swing.JTextField();
        lblPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnAction = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblServer.setText("Server:");

        txtServer.setText("213.73.99.162");

        lblPort.setText("Port:");

        txtPort.setText("33900");

        btnAction.setText("Check for Updates");
        btnAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblServer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtServer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPort)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblServer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPort)
                    .addComponent(lblPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionActionPerformed
        if(isUpToDate){
            Toolkit.getDefaultToolkit().removeAWTEventListener(keyListener);
            if(wasUpdateNeeded){
                System.exit(0);
            }
            else{
                MainFrame mainFrame = new MainFrame(client);
                setVisible(false);
                mainFrame.setVisible(true);
            }
        }
        else{
            checkForUpdates();
        }
    }//GEN-LAST:event_btnActionActionPerformed

    public static void main(String args[]){
        Launcher_Core.initialize();
        Launcher_Client.initialize();
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                new Launcher().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblServer;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtServer;
    // End of variables declaration//GEN-END:variables
}
