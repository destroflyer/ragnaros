/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JTabbedPane;
import ragnaros.client.gui.*;
import ragnaros.core.GameInfo;
import ragnaros.game.Deck;
import ragnaros.network.NetworkUtil;
import ragnaros.network.messages.*;
import com.esotericsoftware.kryonet.*;

/**
 *
 * @author Carl
 */
public class MainFrame extends javax.swing.JFrame{

    public MainFrame(Client client){
        initComponents();
        this.client = client;
        FrameUtil.initWindowSpecials(this);
        FrameUtil.centerFrame(this);
        setTitle(GameInfo.NAME);
        
        tpaneMenu = new JTabbedPane();
        tpaneMenu.add("Deck Editor", new PanDeckEditor(this));
        panJoin = new PanPlay(this);
        addJoinTab();
        
        add(tpaneMenu);
        
        updateCardInfoFrameLocation();
        cardInfoFrame.setVisible(true);
        client.sendTCP(new Message_ClientInitialized());
    }
    private Client client;
    private JTabbedPane tpaneMenu;
    private PanPlay panJoin;
    private PanField panField;
    private CardInfoFrame cardInfoFrame = new CardInfoFrame();
    
    public void enterGameQueue(Deck deck){
        tpaneMenu.remove(panJoin);
        panField = new PanField(this);
        tpaneMenu.add("Play", panField);
        tpaneMenu.setSelectedComponent(panField);
        client.sendTCP(new Message_EnterGameQueue(NetworkUtil.serialize(deck.getPlainTypeSerializer())));
    }
    
    public void leaveGame(){
        panField.close();
        cardInfoFrame.setCardDisplay(null);
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                tpaneMenu.remove(panField);
                addJoinTab();
                tpaneMenu.setSelectedComponent(panJoin);
            }
        });
    }
    
    private void addJoinTab(){
        tpaneMenu.add("Join", panJoin);
    }
    
    private void updateCardInfoFrameLocation(){
        cardInfoFrame.setLocation((int) (getLocation().getX() - cardInfoFrame.getWidth()), (int) (getLocation().getY() + getHeight() - cardInfoFrame.getHeight()));
    }

    public Client getClient(){
        return client;
    }

    public CardInfoFrame getCardInfoFrame(){
        return cardInfoFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        Dimension size = getSize();
        tpaneMenu.setSize((int) size.getWidth(), (int) (size.getHeight() - 33));
        tpaneMenu.setLocation(-2, -2);
    }//GEN-LAST:event_formComponentResized

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        updateCardInfoFrameLocation();
    }//GEN-LAST:event_formComponentMoved

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        cardInfoFrame.toFront();
    }//GEN-LAST:event_formWindowGainedFocus

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
