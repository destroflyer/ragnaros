/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import java.util.HashMap;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JPanel;
import ragnaros.game.*;
import ragnaros.client.gui.CardDisplay;

/**
 *
 * @author Carl
 */
public class PanDeckEditor extends javax.swing.JPanel{

    public PanDeckEditor(MainFrame mainFrame){
        initComponents();
        this.mainFrame = mainFrame;
        initializeLibrary();
        onDeckUpdate();
        hoveredCardDisplay.setIsFront(true);
    }
    public static final String DIRECTORY_DECKS = "./decks/";
    private static final int CARDS_PER_ROW_LIBRARY = 8;
    private static final int CARDS_PER_ROW_DECK = 3;
    private MainFrame mainFrame;
    private int builderX;
    private int builderY;
    private Cards library_All = new Cards();
    private Cards library_Collectibiles = new Cards();
    private PanCard[] panCards_Library_All;
    private PanCard[] panCards_Library_Collectibiles;
    private Deck deck = new Deck();
    private CardDisplay hoveredCardDisplay = new CardDisplay(null);
    private LibraryComparator libraryComparator = new LibraryComparator();
    
    private void initializeLibrary(){
        library_All.clear();
        library_Collectibiles.clear();
        HashMap<Class<? extends Card>, Integer> types = CardTypeManager.getTypes();
        for(int type : types.values()){
            Card card = CardTypeManager.createCard(type);
            library_All.add(card);
            if(card.isCollectible()){
                library_Collectibiles.add(card);
            }
        }
        panCards_Library_All = buildCards(library_All, panLibrary);
        panCards_Library_Collectibiles = buildCards(library_Collectibiles, panLibrary);
    }
    
    private PanCard[] buildCards(Cards cards, JPanel panContainer){
        panContainer.removeAll();
        panContainer.setPreferredSize(new Dimension(panContainer.getWidth(), 0));
        cards.sort(libraryComparator);
        PanCard[] panCards = new PanCard[cards.size()];
        for(int i=0;i<cards.size();i++){
            Card card = cards.get(i);
            PanCard panCard = buildCard(card, panContainer);
            panCards[i] = panCard;
        }
        panContainer.getParent().getParent().setPreferredSize(new Dimension(panContainer.getWidth(), ((panContainer == panDeck)?457:194)));
        panContainer.updateUI();
        return panCards;
    }
    
    private PanCard buildCard(final Card card, final JPanel panContainer){
        final PanCard panCard = new PanCard();
        panCard.setCard(card);
        panContainer.add(panCard);
        panCard.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent evt){
                super.mouseEntered(evt);
                hoveredCardDisplay.setCard(card);
                mainFrame.getCardInfoFrame().setCardDisplay(hoveredCardDisplay);
            }

            @Override
            public void mouseExited(MouseEvent evt){
                super.mouseExited(evt);
                mainFrame.getCardInfoFrame().setCardDisplay(null);
            }

            @Override
            public void mousePressed(MouseEvent evt){
                super.mousePressed(evt);
                if(panContainer == panLibrary){
                    Card clonedCard = CardTypeManager.createCard(CardTypeManager.getType(card));
                    deck.add(clonedCard);
                }
                else{
                    deck.remove(card);
                }
                onDeckUpdate();
            }
        });
        return panCard;
    }
    
    private PanCard[] getPanCards_Library(){
        return (cbxOnlyCollectibiles.isSelected()?panCards_Library_Collectibiles:panCards_Library_All);
    }
    
    private void updateCardPositions(PanCard[] panCards, JPanel panContainer, int cardsPerRow){
        int containerWidth = panContainer.getParent().getWidth();
        int cardWidth = (int) (containerWidth / (float) cardsPerRow);
        int cardHeight = (int) (cardWidth * (4f / 3));
        builderX = 0;
        builderY = 0;
        for(int i=0;i<panCards.length;i++){
            PanCard panCard = panCards[i];
            panCard.setLocation(builderX, builderY);
            panCard.setSize(cardWidth, cardHeight);
            builderX += cardWidth;
            if(((i + 1) % cardsPerRow) == 0){
                builderX = 0;
                builderY += cardHeight;
            }
        }
        if(builderX != 0){
            builderY += cardHeight;
        }
        panContainer.setPreferredSize(new Dimension(containerWidth, builderY));
    }
    
    private void onDeckUpdate(){
        PanCard[] panCards_Deck = buildCards(deck, panDeck);
        updateCardPositions(panCards_Deck, panDeck, CARDS_PER_ROW_DECK);
        txtDeckTitle.setText(deck.getDescription().getTitle());
        lblDeckSize.setText(deck.size() + " cards");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpaneLibrary = new javax.swing.JScrollPane();
        panLibrary = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        scrollpaneDeck = new javax.swing.JScrollPane();
        panDeck = new javax.swing.JPanel();
        lblDeckSize = new javax.swing.JLabel();
        txtDeckTitle = new javax.swing.JTextField();
        cbxOnlyCollectibiles = new javax.swing.JCheckBox();

        scrollpaneLibrary.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpaneLibrary.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollpaneLibraryComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panLibraryLayout = new javax.swing.GroupLayout(panLibrary);
        panLibrary.setLayout(panLibraryLayout);
        panLibraryLayout.setHorizontalGroup(
            panLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );
        panLibraryLayout.setVerticalGroup(
            panLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        scrollpaneLibrary.setViewportView(panLibrary);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        scrollpaneDeck.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpaneDeck.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout panDeckLayout = new javax.swing.GroupLayout(panDeck);
        panDeck.setLayout(panDeckLayout);
        panDeckLayout.setHorizontalGroup(
            panDeckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );
        panDeckLayout.setVerticalGroup(
            panDeckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        scrollpaneDeck.setViewportView(panDeck);

        lblDeckSize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeckSize.setText("0 cards");

        txtDeckTitle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDeckTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeckTitleKeyReleased(evt);
            }
        });

        cbxOnlyCollectibiles.setSelected(true);
        cbxOnlyCollectibiles.setText("Only collectibles");
        cbxOnlyCollectibiles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOnlyCollectibilesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxOnlyCollectibiles)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollpaneLibrary, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDeckTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDeckSize, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollpaneDeck, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDeckSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxOnlyCollectibiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDeckTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpaneLibrary)
                    .addComponent(scrollpaneDeck))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        deck = new Deck();
        onDeckUpdate();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        File file = FrameUtil.chooseFile(true, DIRECTORY_DECKS, DeckFileHandler.FILE_FILTER);
        if(file != null){
            deck = DeckFileHandler.load(file);
            onDeckUpdate();
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        File file = FrameUtil.chooseFile(false, DIRECTORY_DECKS, DeckFileHandler.FILE_FILTER);
        if(file != null){
            DeckFileHandler.save(deck, file);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtDeckTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeckTitleKeyReleased
        deck.setDescription(new Description(txtDeckTitle.getText(), deck.getDescription().getDescription()));
    }//GEN-LAST:event_txtDeckTitleKeyReleased

    private void scrollpaneLibraryComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_scrollpaneLibraryComponentResized
        PanCard[] panCards_Library = getPanCards_Library();
        updateCardPositions(panCards_Library, panLibrary, CARDS_PER_ROW_LIBRARY);
    }//GEN-LAST:event_scrollpaneLibraryComponentResized

    private void cbxOnlyCollectibilesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOnlyCollectibilesItemStateChanged
        PanCard[] panCards_Library = getPanCards_Library();
        panLibrary.removeAll();
        for(PanCard panCard : panCards_Library){
            panLibrary.add(panCard);
        }
        updateCardPositions(panCards_Library, panLibrary, CARDS_PER_ROW_LIBRARY);
        panLibrary.updateUI();
    }//GEN-LAST:event_cbxOnlyCollectibilesItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbxOnlyCollectibiles;
    private javax.swing.JLabel lblDeckSize;
    private javax.swing.JPanel panDeck;
    private javax.swing.JPanel panLibrary;
    private javax.swing.JScrollPane scrollpaneDeck;
    private javax.swing.JScrollPane scrollpaneLibrary;
    private javax.swing.JTextField txtDeckTitle;
    // End of variables declaration//GEN-END:variables
}
