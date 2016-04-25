/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client.gui;

import ragnaros.core.GameInfo;
import ragnaros.core.Util;
import ragnaros.network.messages.Message_GameOver;
import ragnaros.network.messages.Message_GameUpdate;
import ragnaros.network.messages.Message_Command;
import ragnaros.network.messages.Message_ClientAccepted;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JOptionPane;
import ragnaros.client.*;
import ragnaros.game.*;
import ragnaros.game.bots.*;
import ragnaros.game.commands.*;
import ragnaros.game.mechanics.*;
import ragnaros.game.modes.*;
import ragnaros.network.NetworkUtil;
import com.esotericsoftware.kryonet.*;

/**
 *
 * @author Carl
 */
public class PanField extends javax.swing.JPanel{

    public PanField(final MainFrame mainFrame){
        initComponents();
        this.mainFrame = mainFrame;
        this.cardInfoFrame = mainFrame.getCardInfoFrame();
        addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent evt){
                super.mousePressed(evt);
                if(isInputAllowed()){
                    CardChooser activeCardChooser = cardChooserManager.getActiveCardChooser();
                    if(evt.getModifiers() == MouseEvent.BUTTON3_MASK){
                        if(activeCardChooser != null){
                            cardChooserManager.stop();
                        }
                        cancelDragging(evt);
                    }
                    else{
                        if(hoveredCardDisplay != null){
                            Card card = hoveredCardDisplay.getCard();
                            if(activeCardChooser != null){
                                if(activeCardChooser.isValidChoice(card)){
                                    int cardIndex = card.getCardPosition().getIndex();
                                    if(activeCardChooser instanceof ManaChooser){
                                        ManaChooser manaChooser = (ManaChooser) activeCardChooser;
                                        FieldCard fieldCard = (FieldCard) card;
                                        int manaSpellIndex = selectFieldCardSpell(fieldCard, "Choose Mana Spell:", manaChooser);
                                        if(manaSpellIndex != -1){
                                            ManaSpell manaSpell = (ManaSpell) fieldCard.getSpells()[manaSpellIndex];
                                            manaChooser.addManaSpell(manaSpell);
                                            checkManaChooser(manaChooser);
                                        }
                                    }
                                    else if(activeCardChooser instanceof SpellParameterChooser){
                                        SpellParameterChooser spellParameterChooser = (SpellParameterChooser) activeCardChooser;
                                        spellParameterChooser.setNextParameter(card);
                                        checkSpellParameterChooser();
                                    }
                                    else if(activeCardChooser instanceof ThrowingOffChooser){
                                        ThrowingOffChooser throwingOffChooser = (ThrowingOffChooser) activeCardChooser;
                                        if(card.getOwner().getHand().size() <= (game.getGameMode().getMaximumHandCards(game.getCurrentPlayer()) + 1)){
                                            cardChooserManager.stop();
                                        }
                                        commandSender.sendCommand(new ThrowingOffCommand(cardIndex));
                                    }
                                }
                            }
                            else if(highlightedCardDisplay != null){
                                draggedCardDisplay = hoveredCardDisplay;
                                draggedCardDisplay.saveTempLocation();
                                updateCursor();
                            }
                            else if(bot != null){
                                if((card.getOwner().getIndex() == playerIndex) && (card.getCardPosition().getZone() == CardPosition.Zone.DECK)){
                                    bot.performTurn();
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt){
                super.mouseReleased(evt);
                if(isInputAllowed()){
                    if(draggedCardDisplay != null){
                        Card card = draggedCardDisplay.getCard();
                        int y = getGUI_Y(evt.getY());
                        if(card.getCardPosition().getZone() == CardPosition.Zone.HAND){
                            boolean isHoveringField = (y < 2000);
                            if(isHoveringField){
                                if(card instanceof FieldCard){
                                    FieldCard fieldCard = (FieldCard) card;
                                    ManaAmount neededMana = fieldCard.getCastCost().getMana().getNeededAmount(game.getPlayer(playerIndex).getAvailableMana());
                                    cardChooserManager.startChoosing_FieldCardCastCost(playerIndex, fieldCard, neededMana);
                                    chooseManaAutomatically(neededMana);
                                }
                                else if(card instanceof SpellCard){
                                    SpellCard spellCard = (SpellCard) card;
                                    Spell spell = spellCard.getSpell();
                                    ManaAmount neededMana = spell.getCost().getMana().getNeededAmount(game.getPlayer(playerIndex).getAvailableMana());
                                    cardChooserManager.startChoosing_SpellCost(playerIndex, spell, neededMana);
                                    chooseManaAutomatically(neededMana);
                                }
                            }
                        }
                        else if(card.getCardPosition().getZone() == CardPosition.Zone.MONSTER){
                            boolean isHoveringEnemy = (y < 1200);
                            if(isHoveringEnemy){
                                int enemyMonstersCount = game.getPlayer(playerIndex).getEnemy().getMonsters().size();
                                if(enemyMonstersCount == 0){
                                    commandSender.sendCommand(new AttackPlayerCommand(card.getCardPosition().getIndex()));
                                }
                                else{
                                    int targetMonsterIndex = -1;
                                    if(enemyMonstersCount == 1){
                                        targetMonsterIndex = 0;
                                    }
                                    else if((hoveredCardDisplay != null) && (hoveredCardDisplay.getCard().getCardPosition().getZone() == CardPosition.Zone.MONSTER) && (hoveredCardDisplay.getCard().getOwner().getIndex() != playerIndex)){
                                        targetMonsterIndex = hoveredCardDisplay.getCard().getCardPosition().getIndex();
                                    }
                                    if(targetMonsterIndex != -1){
                                        commandSender.sendCommand(new AttackMonsterCommand(card.getCardPosition().getIndex(), targetMonsterIndex));
                                    }
                                }
                            }
                            isDraggingTargetArrow = false;
                        }
                        cancelDragging(evt);
                    }
                    else if(evt.getModifiers() == MouseEvent.BUTTON3_MASK){
                        if(hoveredCardDisplay != null){
                            Card card = hoveredCardDisplay.getCard();
                            if((card.getOwner().getIndex() == playerIndex)
                            && ((card.getCardPosition().getZone() == CardPosition.Zone.LAND) || (card.getCardPosition().getZone() == CardPosition.Zone.MONSTER))){
                                FieldCard fieldCard = (FieldCard) card;
                                int spellIndex = selectFieldCardSpell(fieldCard, "Choose Spell:", null);
                                if(spellIndex != -1){
                                    Spell spell = fieldCard.getSpells()[spellIndex];
                                    ManaAmount neededMana = spell.getCost().getMana().getNeededAmount(game.getPlayer(playerIndex).getAvailableMana());
                                    cardChooserManager.startChoosing_FieldCardSpellCost(playerIndex, spell, spellIndex, neededMana);
                                    chooseManaAutomatically(neededMana);
                                }
                            }
                        }
                    }
                    else if(isEndTurnButtonHovered){
                        if(cardChooserManager.getActiveCardChooser() != null){
                            cardChooserManager.stop();
                        }
                        else{
                            if(game.getPlayer(game.getCurrentPlayerIndex()).getHand().size() > game.getGameMode().getMaximumHandCards(game.getCurrentPlayer())){
                                cardChooserManager.startChoosing_ThrowingOff(game.getCurrentPlayerIndex());
                            }
                            else{
                                isEndTurnButtonHovered = false;
                            }
                            commandSender.sendCommand(new EndTurnCommand());
                        }
                    }
                    updateHightlightedCardDisplay();
                }
            }
            
            private void cancelDragging(MouseEvent evt){
                if(draggedCardDisplay != null){
                    draggedCardDisplay.targetTempLocation();
                    draggedCardDisplay = null;
                }
                updateMousePosition(evt);
                updateButtonsHoverInformation();
            }
            
            private int selectFieldCardSpell(FieldCard fieldCard,  String message, ManaChooser manaChooser){
                LinkedList<Spell> allowedSpells = new LinkedList<Spell>();
                boolean isAllowed;
                for(int i=0;i<fieldCard.getSpells().length;i++){
                    Spell spell = fieldCard.getSpells()[i];
                    if(manaChooser != null){
                        isAllowed = ((spell instanceof ManaSpell) && manaChooser.isValidChoice((ManaSpell) spell) && fieldCard.getOwner().canCast(spell) && (spell.getSpellParameterFormat().size() == 0));
                    }
                    else{
                        //Missing mana can still be selected at this point
                        isAllowed = isCostPossible(fieldCard.getOwner(), spell.getCost(), fieldCard);
                    }
                    if(isAllowed){
                        allowedSpells.add(spell);
                    }
                }
                int spellIndex = -1;
                if(allowedSpells.size() > 0){
                    if(allowedSpells.size() > 1){
                        String[] spellTitles = new String[allowedSpells.size()];
                        int i = 0;
                        for(Spell spell : allowedSpells){
                            spellTitles[i] = spell.getDescription().getTitle();
                            i++;
                        }
                        spellIndex = JOptionPane.showOptionDialog(PanField.this, message, GameInfo.NAME, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, spellTitles, null);
                    }
                    else{
                        spellIndex = getSpellIndex(fieldCard, allowedSpells.get(0));
                    }
                }
                return spellIndex;
            }
            
            private void chooseManaAutomatically(ManaAmount manaAmount){
                ManaChooser manaChooser = (ManaChooser) cardChooserManager.getActiveCardChooser();
                if(!manaAmount.isEmpty()){
                    List<ManaSpell[]> manaSpellsCombinations = getManaSpellsCombinations(game.getPlayer(playerIndex), manaAmount);
                    if(manaSpellsCombinations.size() == 1){
                        ManaSpell[] automatedManaSpells = manaSpellsCombinations.get(0);
                        for(ManaSpell manaSpell : automatedManaSpells){
                            manaChooser.addManaSpell(manaSpell);
                        }
                    }
                }
                checkManaChooser(manaChooser);
            }
            
            private void checkManaChooser(ManaChooser manaChooser){
                if(manaChooser.isFinished()){
                    switch(manaChooser.getType()){
                        case FIELD_CARD_CAST_COST:
                            int handCardIndex_1 = manaChooser.getChooseInformation()[0];
                            FieldCard fieldCard_1 = (FieldCard) game.getPlayer(playerIndex).getHand().get(handCardIndex_1);
                            Spell summonSpell = fieldCard_1.getCastSpell();
                            if((summonSpell != null) && (fieldCard_1.isCastSpellObligatory() || summonSpell.isValidTargetAvailable())){
                                cardChooserManager.startChoosing_FieldCardCastSpellParameter(summonSpell, manaChooser.getManaSpells());
                                checkSpellParameterChooser();
                            }
                            else{
                                castManaSpells(manaChooser.getManaSpells());
                                castFieldCardWithoutCastSpell(handCardIndex_1);
                                cardChooserManager.stop();
                            }
                            break;

                        case FIELD_CARD_SPELL_COST:
                            CardPosition cardPosition = new CardPosition(CardPosition.Zone.values()[manaChooser.getChooseInformation()[0]], manaChooser.getChooseInformation()[1]);
                            int spellIndex = manaChooser.getChooseInformation()[2];
                            FieldCard fieldCard_2 = (FieldCard) game.getPlayer(playerIndex).getCard(cardPosition);
                            Spell spell_1 = fieldCard_2.getSpells()[spellIndex];
                            cardChooserManager.startChoosing_FieldCardSpellParameter(spell_1, manaChooser.getManaSpells());
                            checkSpellParameterChooser();
                            break;

                        case SPELL_COST:
                            int handCardIndex_2 = manaChooser.getChooseInformation()[0];
                            SpellCard spellCard = (SpellCard) game.getPlayer(playerIndex).getHand().get(handCardIndex_2);
                            Spell spell_2 = spellCard.getSpell();
                            cardChooserManager.startChoosing_SpellParameter(spell_2, manaChooser.getManaSpells());
                            checkSpellParameterChooser();
                            break;
                    }
                }
            }
            
            private void castFieldCardWithoutCastSpell(int handCardIndex){
                commandSender.sendCommand(new CastFieldCardCommand(handCardIndex, null));
            }
    
            private void checkSpellParameterChooser(){
                SpellParameterChooser spellParameterChooser = (SpellParameterChooser) cardChooserManager.getActiveCardChooser();
                if(spellParameterChooser.isFinished()){
                    castManaSpells(spellParameterChooser.getSpell_ManaSpells());
                    switch(spellParameterChooser.getType()){
                        case FIELD_CARD_CAST_SPELL:
                            Card caster_1 = spellParameterChooser.getSpell().getCaster();
                            int handCardIndex_1 = caster_1.getCardPosition().getIndex();
                            commandSender.sendCommand(new CastFieldCardCommand(handCardIndex_1, spellParameterChooser.getSpellParameters()));
                            break;
                        
                        case FIELD_CARD_SPELL:
                            FieldCard caster_2 = (FieldCard) spellParameterChooser.getSpell().getCaster();
                            int spellIndex = getSpellIndex(caster_2, spellParameterChooser.getSpell());
                            commandSender.sendCommand(new CastFieldCardSpellCommand(caster_2.getCardPosition(), spellIndex, spellParameterChooser.getSpellParameters()));
                            break;

                        case SPELL:
                            Card caster_3 = spellParameterChooser.getSpell().getCaster();
                            int handCardIndex_2 = caster_3.getCardPosition().getIndex();
                            commandSender.sendCommand(new CastSpellCommand(handCardIndex_2, spellParameterChooser.getSpellParameters()));
                            break;
                    }
                    cardChooserManager.stop();
                }
            }
            
            private void castManaSpells(ManaSpell[] manaSpells){
                for(ManaSpell manaSpell : manaSpells){
                    FieldCard caster = (FieldCard) manaSpell.getCaster();
                    int spellIndex = getSpellIndex(caster, manaSpell);
                    commandSender.sendCommand(new CastFieldCardSpellCommand(caster.getCardPosition(), spellIndex, null));
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter(){

            @Override
            public void mouseMoved(MouseEvent evt){
                super.mouseMoved(evt);
                updateMousePosition(evt);
                if(isInputAllowed()){
                    updateButtonsHoverInformation();
                }
            }

            @Override
            public void mouseDragged(MouseEvent evt){
                super.mouseDragged(evt);
                updateMousePosition(evt);
                if(isInputAllowed()){
                    updateButtonsHoverInformation();
                    if(draggedCardDisplay != null){
                        Card card = draggedCardDisplay.getCard();
                        switch(card.getCardPosition().getZone()){
                            case HAND:
                                int x = (mousePosition_X - (CardDisplay.WIDTH / 2));
                                int y = (mousePosition_Y - (CardDisplay.HEIGHT / 2));
                                draggedCardDisplay.setLocation(x, y);
                                break;
                            
                            case MONSTER:
                                MonsterCard monsterCard = (MonsterCard) card;
                                if(monsterCard.canAttack()){
                                    isDraggingTargetArrow = true;
                                }
                                break;
                        }
                    }
                }
            }
        });
        mainFrame.getClient().addListener(gameListener);
        game = new Game(false);
        game.setPlayers(new Player(), new Player());
        new Thread(new Runnable(){

            @Override
            public void run(){
                long lastFrameTimestamp = System.currentTimeMillis();
                while(!shouldClose){
                    long currentTimestamp = System.currentTimeMillis();
                    lastTimePerFrame = ((currentTimestamp - lastFrameTimestamp) / 1000f);
                    lastFrameTimestamp = currentTimestamp;
                    EventQueue.invokeLater(guiUpdater);
                    try{
                        Thread.sleep(1000 / 60);
                    }catch(InterruptedException ex){
                    }
                }
                mainFrame.getClient().removeListener(gameListener);
            }
        }).start();
    }
    private final static int GUI_WIDTH = 2100;
    private final static int GUI_HEIGHT = 2400;
    private MainFrame mainFrame;
    private CommandSender commandSender = new CommandSender(){

        @Override
        public void sendCommand(Command command){
            mainFrame.getClient().sendTCP(new Message_Command(command));
        }
    };
    private CardInfoFrame cardInfoFrame;
    private Game game;
    private int playerIndex;
    private Bot bot;
    private CardChooserManager cardChooserManager = new CardChooserManager(){

        @Override
        public void setMessage(String text){
            message = text;
        }
    };
    private float lastTimePerFrame;
    private boolean shouldClose;
    private Runnable guiUpdater = new Runnable(){

        @Override
        public void run(){
            update(lastTimePerFrame);
        }
    };
    private Listener gameListener = new Listener(){

        @Override
        public void received(Connection connection, Object receivedMessage){
            if(receivedMessage instanceof Message_ClientAccepted){
                Message_ClientAccepted message = (Message_ClientAccepted) receivedMessage;
                GameMode gameMode = Util.createObjectByClassName(message.getGameModeClassName(), GameMode.class);
                game.setGameMode(gameMode);
                playerIndex = message.getPlayerIndex();
                setBot(manualBot);
                for(int i=0;i<bots.length;i++){
                    if(bots[i] != null){
                        bots[i].initialize(game, commandSender);
                    }
                }
            }
            else if(receivedMessage instanceof Message_GameUpdate){
                Message_GameUpdate message = (Message_GameUpdate) receivedMessage;
                NetworkUtil.unserialize(game, message.getData());
                if((game.getCurrentPlayerIndex() != tmpCurrentPlayerIndex) && (game.getCurrentPlayerIndex() == playerIndex)){
                    final Bot bot = bots[playerIndex];
                    if(bot != null){
                        new Thread(new Runnable(){

                            @Override
                            public void run(){
                                try{
                                    Thread.sleep(botTurnDelay);
                                }catch(InterruptedException ex){
                                }
                                bot.performTurn();
                            }
                        }).start();
                    }
                }
                tmpCurrentPlayerIndex = game.getCurrentPlayerIndex();
            }
            else if(receivedMessage instanceof Message_GameOver){
                Message_GameOver message = (Message_GameOver) receivedMessage;
                boolean isVictory = (message.getWinnerPlayerIndex() == playerIndex);
                FrameUtil.showMessageDialog(PanField.this, (isVictory?"Victory!":"Defeat!"), FrameUtil.MessageType.INFORMATION);
                mainFrame.leaveGame();
            }
        }
    };
    public static final GameMode GAME_MODE = new DestroMode();
    private Bot manualBot = new IntelligentBot();
    private Bot[] bots = new Bot[]{
        null,//new Etherbot(20),
        null//new IntelligentBot()
    };
    private int tmpCurrentPlayerIndex = -1;
    private int botTurnDelay = 200;
    private Graphics2D graphics;
    private final Font fontButton = new Font("Tahoma", Font.BOLD, 70);
    private final Font fontPlayerLifepoints = new Font("Tahoma", Font.BOLD, 70);
    private final Font fontDeckSize = new Font("Tahoma", Font.BOLD, 70);
    private static final Font fontTitle = new Font("Tahoma", Font.BOLD, 18);
    private static final Font fontDescription = new Font("Tahoma", Font.PLAIN, 13);
    private static final Font fontKeywords = new Font("Tahoma", Font.BOLD, 13);
    private static final Font fontEffects = new Font("Tahoma", Font.PLAIN, 13);
    private static final Font fontFlavorText = new Font("Tahoma", Font.ITALIC, 13);
    private static final Font fontTribes = new Font("Tahoma", Font.BOLD, 13);
    private static final Font fontStats = new Font("Tahoma", Font.BOLD, 35);
    private static final Font fontMessage = new Font("Tahoma", Font.PLAIN, 90);
    private ConcurrentHashMap<Card, CardDisplay> cardDisplays = new ConcurrentHashMap<Card, CardDisplay>();
    private ArrayList<CardDisplay> sortedCardDisplays = new ArrayList<CardDisplay>();
    private CardDisplay hoveredCardDisplay;
    private CardDisplay highlightedCardDisplay;
    private CardDisplay draggedCardDisplay;
    private int mousePosition_X;
    private int mousePosition_Y;
    private boolean isDraggingTargetArrow;
    private static final BasicStroke targetArrow_Stroke = new BasicStroke(12);
    private static final int targetArrow_CircleRadius = 50;
    private double offsetX;
    private double offsetY;
    private double scale;
    private Cursor cursorDefault = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor cursorDrag = DragSource.DefaultMoveDrop;
    private Cursor cursorHand = new Cursor(Cursor.HAND_CURSOR);
    private int endTurnButtonX = 1800;
    private int endTurnButtonY = 1200;
    private boolean isEndTurnButtonHovered;
    private String message;
    private Comparator<CardDisplay> drawCardDisplayComparator = new Comparator<CardDisplay>(){

        @Override
        public int compare(CardDisplay cardDisplay1, CardDisplay cardDisplay2){
            return (cardDisplay1.getCard().getCardPosition().getIndex() - cardDisplay2.getCard().getCardPosition().getIndex());
        }
    };

    public void setGame(Game game){
        this.game = game;
        updateCardLocations();
    }

    public void setBot(Bot bot){
        this.bot = bot;
        if(bot != null){
            bot.initialize(game, commandSender);
        }
    }
    
    public void close(){
        shouldClose = true;
    }
    
    private boolean isInputAllowed(){
        return (game.isRunning() && (game.getCurrentPlayerIndex() == playerIndex));
    }
    
    private void updateMousePosition(MouseEvent evt){
        mousePosition_X = getGUI_X(evt.getX());
        mousePosition_Y = getGUI_Y(evt.getY());
        CardDisplay oldHoveredCardDisplay = hoveredCardDisplay;
        hoveredCardDisplay = null;
        for(int i=(sortedCardDisplays.size()-1);i>=0;i--){
            CardDisplay cardDisplay = sortedCardDisplays.get(i);
            if((cardDisplay != draggedCardDisplay) && cardDisplay.intersects(mousePosition_X, mousePosition_Y)){
                hoveredCardDisplay = cardDisplay;
                break;
            }
        }
        if(hoveredCardDisplay != oldHoveredCardDisplay){
            updateHightlightedCardDisplay();
        }
        cardInfoFrame.setCardDisplay(hoveredCardDisplay);
    }
    
    private void updateHightlightedCardDisplay(){
        if(highlightedCardDisplay != null){
            highlightedCardDisplay.setIsHighlighted(false);
            highlightedCardDisplay = null;
        }
        if(hoveredCardDisplay != null){
            if(((cardChooserManager.getActiveCardChooser() == null) && isDraggingActionAllowed(hoveredCardDisplay.getCard()))
            || ((cardChooserManager.getActiveCardChooser() != null) && cardChooserManager.getActiveCardChooser().isValidChoice(hoveredCardDisplay.getCard()))){
                highlightedCardDisplay = hoveredCardDisplay;
                highlightedCardDisplay.setIsHighlighted(true);
            }
        }
    }
    
    private void updateButtonsHoverInformation(){
        isEndTurnButtonHovered = ((mousePosition_X >= endTurnButtonX) && (mousePosition_Y >= endTurnButtonY) && (mousePosition_X < (endTurnButtonX + CardDisplay.WIDTH)) && (mousePosition_Y < (endTurnButtonY + CardDisplay.HEIGHT)));
        updateCursor();
    }
    
    private void updateCursor(){
        Cursor cursor = cursorDefault;
        if(draggedCardDisplay != null){
            cursor = cursorDrag;
        }
        else if(isEndTurnButtonHovered){
            cursor = cursorHand;
        }
        setCursor(cursor);
    }
    
    private boolean isDraggingActionAllowed(Card card){
        boolean isDraggingAllowed = false;
        Player player = game.getPlayer(playerIndex);
        if(card.getOwner().getIndex() == playerIndex){
            switch(card.getCardPosition().getZone()){
                case HAND:
                    if(card instanceof FieldCard){
                        FieldCard fieldCard = (FieldCard) card;
                        isDraggingAllowed = isCostPossible(player, fieldCard.getCastCost(), fieldCard);
                        Spell castSpell = fieldCard.getCastSpell();
                        if((castSpell != null) && fieldCard.isCastSpellObligatory()){
                            isDraggingAllowed &= castSpell.isValidTargetAvailable();
                        }
                        if(card instanceof Land){
                            isDraggingAllowed &= player.canAddLand();
                        }
                    }
                    else if(card instanceof SpellCard){
                        SpellCard spellCard = (SpellCard) card;
                        isDraggingAllowed = isCostPossible(player, spellCard.getSpell().getCost(), spellCard);
                    }
                    break;

                case LAND:
                case MONSTER:
                    if(card instanceof FieldCard){
                        FieldCard fieldCard = (FieldCard) card;
                        for(Spell spell : fieldCard.getSpells()){
                            if(player.canCast(spell)){
                                isDraggingAllowed = true;
                                break;
                            }
                        }
                        if(card instanceof MonsterCard){
                            MonsterCard monsterCard = (MonsterCard) card;
                            isDraggingAllowed |= monsterCard.canAttack();
                        }
                    }
                    break;
            }
        }
        return isDraggingAllowed;
    }
    
    private boolean isCostPossible(Player player, Cost cost, Card card){
        if((cost.isTap() && ((FieldCard) card).isTapped())
        || (player.getLifepoints() <= cost.getLifepoints())){
            return false;
        }
        if(!player.hasEnoughMana(cost.getMana())){
            ManaAmount neededMana = cost.getMana().clone();
            neededMana.subtractFromRemaining(player.getAvailableMana());
            List<ManaSpell[]> manaSpellsCombinations = getManaSpellsCombinations(player, neededMana);
            return (manaSpellsCombinations.size() > 0);
        }
        return true;
    }
    
    private static LinkedList<ManaSpell> tmpManaSpells = new LinkedList<ManaSpell>();
    private static ManaAmount tmpRemainingManaAmount = new ManaAmount();
    private static List<ManaSpell[]> getManaSpellsCombinations(Player player, ManaAmount manaAmount){
        tmpManaSpells.clear();
        addAllAutomatableManaSpells(tmpManaSpells, player.getLands());
        addAllAutomatableManaSpells(tmpManaSpells, player.getMonsters());
        ManaSpell[] manaSpells = tmpManaSpells.toArray(new ManaSpell[tmpManaSpells.size()]);
        List<ManaSpell[]> manaSpellsCombinations = Util.getAllCombinations(manaSpells, ManaSpell.class);
        for(int i=0;i<manaSpellsCombinations.size();i++){
            ManaSpell[] manaSpellsCombination = manaSpellsCombinations.get(i);
            tmpRemainingManaAmount.set(manaAmount);
            boolean removeCombination = true;
            if(!containsCasterMultipleTimes(manaSpellsCombination)){
                for(int r=0;r<manaSpellsCombination.length;r++){
                    ManaAmount gainedManaAmount = manaSpellsCombination[r].getGainedManaAmount();
                    if(!gainedManaAmount.canBeUsedToPay(tmpRemainingManaAmount)){
                        removeCombination = true;
                        break;
                    }
                    tmpRemainingManaAmount.subtractFromRemaining(gainedManaAmount);
                    if(tmpRemainingManaAmount.isEmpty()){
                        for(int z=0;z<manaSpellsCombinations.size();z++){
                            ManaSpell[] otherCombination = manaSpellsCombinations.get(z);
                            if(isParentCombination(manaSpellsCombination, otherCombination)
                            || isClassInstanceVariation(manaSpellsCombination, otherCombination)){
                                manaSpellsCombinations.remove(z);
                                if(z < i){
                                    i--;
                                }
                                z--;
                            }
                        }
                        removeCombination = (r < (manaSpellsCombination.length - 1));
                        break;
                    }
                }
            }
            if(removeCombination){
                manaSpellsCombinations.remove(i);
                i--;
            }
        }
        return manaSpellsCombinations;
    }

    private static void addAllAutomatableManaSpells(LinkedList<ManaSpell> manaSpells, Cards fieldCards){
        for(int i=0;i<fieldCards.size();i++){
            FieldCard fieldCard = (FieldCard) fieldCards.get(i);
            for(Spell spell : fieldCard.getSpells()){
                if((spell instanceof ManaSpell) && fieldCard.getOwner().canCast(spell) && (spell.getSpellParameterFormat().size() == 0)){
                    ManaSpell manaSpell = (ManaSpell) spell;
                    manaSpells.add(manaSpell);
                }
            }
        }
    }

    private static boolean containsCasterMultipleTimes(ManaSpell[] combination){
        for(int i=0;i<combination.length;i++){
            for(int r=(i+1);r<combination.length;r++){
                if(combination[i].getCaster() == combination[r].getCaster()){
                    return true;
                }
            }
        }
        return false;
    }

    private static <T> boolean isParentCombination(T[] parentCombination, T[] childCombination){
        if(parentCombination.length >= childCombination.length){
            return false;
        }
        for(int i=0;i<parentCombination.length;i++){
            if(childCombination[i] != parentCombination[i]){
                return false;
            }
        }
        return true;
    }

    private static boolean isClassInstanceVariation(ManaSpell[] combination1, ManaSpell[] combination2){
        if((combination1 == combination2) || (combination1.length != combination2.length)){
            return false;
        }
        for(int i=0;i<combination1.length;i++){
            ManaSpell spell1 = combination1[i];
            ManaSpell spell2 = combination2[i];
            if(spell1.getCaster().getClass() != spell2.getCaster().getClass()){
                return false;
            }
            int spellIndex1 = getSpellIndex((FieldCard) spell1.getCaster(), spell1);
            int spellIndex2 = getSpellIndex((FieldCard) spell2.getCaster(), spell2);
            if(spellIndex1 != spellIndex2){
                return false;
            }
        }
        return true;
    }
            
    private static int getSpellIndex(FieldCard fieldCard, Spell spell){
        for(int i=0;i<fieldCard.getSpells().length;i++){
            if(fieldCard.getSpells()[i] == spell){
                return i;
            }
        }
        return -1;
    }
    
    private int getGUI_X(int panelX){
        return (int) (((panelX - offsetX) / scale));
    }
    
    private int getGUI_Y(int panelY){
        return (int) (((panelY - offsetY) / scale));
    }
    
    public void update(float lastTimePerFrame){
        if(game != null){
            sortedCardDisplays = new ArrayList<CardDisplay>(cardDisplays.values());
            Collections.sort(sortedCardDisplays, drawCardDisplayComparator);
            updateCardLocations();
            for(CardDisplay cardDisplay : sortedCardDisplays){
                cardDisplay.update(lastTimePerFrame);
            }
        }
        updateUI();
    }
    
    private void updateCardLocations(){
        updateCardLocations(0);
        updateCardLocations(1);
    }
    
    private void updateCardLocations(int playerIndex){
        boolean isOwnPlayer = (playerIndex == this.playerIndex);
        Deck deck = game.getPlayer(playerIndex).getDeck();
        for(int i=0;i<deck.size();i++){
            Card card = deck.get(i);
            CardDisplay cardDisplay = getCardDisplay(card);
            cardDisplay.setIsFront(false);
            updateCardLocation(cardDisplay, 1800, (isOwnPlayer?2000:0));
        }
        Hand hand = game.getPlayer(playerIndex).getHand();
        int x = getCardsRow_Start(hand);
        for(int i=0;i<hand.size();i++){
            Card card = hand.get(i);
            CardDisplay cardDisplay = getCardDisplay(card);
            cardDisplay.setIsFront(isOwnPlayer);
            if(updateCardLocation(cardDisplay, x, (isOwnPlayer?2000:0))){
                x += getCardsRow_Interval(hand);
            }
        }
        Cards lands = game.getPlayer(playerIndex).getLands();
        x = getCardsRow_Start(lands);
        for(int i=0;i<lands.size();i++){
            Card card = lands.get(i);
            CardDisplay cardDisplay = getCardDisplay(card);
            cardDisplay.setIsFront(true);
            updateCardLocation(cardDisplay, x, (isOwnPlayer?1600:400));
            x += getCardsRow_Interval(lands);
        }
        Cards monsters = game.getPlayer(playerIndex).getMonsters();
        x = getCardsRow_Start(monsters);
        for(int i=0;i<monsters.size();i++){
            Card card = monsters.get(i);
            CardDisplay cardDisplay = getCardDisplay(card);
            cardDisplay.setIsFront(true);
            updateCardLocation(cardDisplay, x, (isOwnPlayer?1200:800));
            x += getCardsRow_Interval(monsters);
        }
        Graveyard graveyard = game.getPlayer(playerIndex).getGraveyard();
        for(int i=0;i<graveyard.size();i++){
            Card card = graveyard.get(i);
            CardDisplay cardDisplay = getCardDisplay(card);
            cardDisplay.setIsFront(true);
            updateCardLocation(cardDisplay, 1800, (isOwnPlayer?1600:400));
        }
    }
    
    private int getCardsRow_Start(Cards cards){
        int cardsSize = cards.size();
        if((cards instanceof Hand) && (draggedCardDisplay != null) && cards.contains(draggedCardDisplay.getCard())){
            cardsSize--;
        }
        return ((1800 - (Math.min(cardsSize, 6) * 300)) / 2);
    }
    
    private int getCardsRow_Interval(Cards cards){
        return (1800 / Math.max(6, cards.size()));
    }
    
    private CardDisplay getCardDisplay(Card card){
        CardDisplay cardDisplay = cardDisplays.get(card);
        if(cardDisplay == null){
            cardDisplay = new CardDisplay(card);
            cardDisplays.put(card, cardDisplay);
        }
        return cardDisplay;
    }
    
    private boolean updateCardLocation(CardDisplay cardDisplay, int targetX, int targetY){
        if(cardDisplay != draggedCardDisplay){
            cardDisplay.setTargetLocation(targetX, targetY);
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics _graphics){
        super.paintComponent(_graphics);
        graphics = (Graphics2D) _graphics.create();
        graphics.drawImage(CardImages.getCachedImage("images/table.jpg"), 0, 0, getWidth(), getHeight(), this);
        graphics.translate(offsetX, offsetY);
        graphics.scale(scale, scale);
        graphics.drawImage(CardImages.getCachedImage("images/button" + (isEndTurnButtonHovered?"_hover":"") + ".png"), endTurnButtonX, endTurnButtonY, 300, 400, this);
        graphics.setFont(fontButton);
        graphics.setColor(Color.BLACK);
        if(game.getCurrentPlayerIndex() == playerIndex){
            if(cardChooserManager.getActiveCardChooser() != null){
                graphics.drawString("Cancel", endTurnButtonX + 33, endTurnButtonY + 225);
            }
            else{
                graphics.drawString("End", endTurnButtonX + 83, endTurnButtonY + 180);
                graphics.drawString("Turn", endTurnButtonX + 69, endTurnButtonY + 275);
            }
        }
        else{
            graphics.drawString("Enemy", endTurnButtonX + 30, endTurnButtonY + 180);
            graphics.drawString("Turn", endTurnButtonX + 69, endTurnButtonY + 275);
        }
        for(CardDisplay cardDisplay : sortedCardDisplays.toArray(new CardDisplay[sortedCardDisplays.size()])){
            if(cardDisplay != draggedCardDisplay){
                drawCardDisplay(cardDisplay);
            }
        }
        graphics.setFont(fontDeckSize);
        graphics.setColor(Color.WHITE);
        int deckSizePlayer = game.getPlayer(playerIndex).getDeck().size();
        int deckSizeEnemy = game.getPlayer(playerIndex).getEnemy().getDeck().size();
        graphics.drawString("" + deckSizePlayer, 1800 + ((deckSizePlayer < 10)?127:105), 2230);
        graphics.drawString("" + deckSizeEnemy, 1800 + ((deckSizeEnemy < 10)?127:105), 228);
        drawPlayerLifepoints();
        if(message != null){
            graphics.setFont(fontMessage);
            graphics.setColor(Color.WHITE);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            Rectangle2D messageBounds = fontMetrics.getStringBounds(message, graphics);
            graphics.drawString(message, (int) ((2100 - messageBounds.getWidth()) / 2), 1028);
        }
        if(draggedCardDisplay != null){
            drawCardDisplay(draggedCardDisplay);
            if(isDraggingTargetArrow){
                graphics.setColor(Color.RED);
                graphics.setStroke(targetArrow_Stroke);
                int arrowSource_X = (draggedCardDisplay.getX() + (CardDisplay.WIDTH / 2));
                int arrowSource_Y = (draggedCardDisplay.getY() + (CardDisplay.HEIGHT / 2));
                int arrowDestination_X = mousePosition_X;
                int arrowDestination_Y = mousePosition_Y;
                if(hoveredCardDisplay != null){
                    arrowDestination_X = (hoveredCardDisplay.getX() + (CardDisplay.WIDTH / 2));
                    arrowDestination_Y = (hoveredCardDisplay.getY() + (CardDisplay.HEIGHT / 2));
                }
                graphics.drawLine(arrowSource_X, arrowSource_Y, arrowDestination_X, arrowDestination_Y);
                graphics.drawOval((arrowDestination_X - targetArrow_CircleRadius), (arrowDestination_Y - targetArrow_CircleRadius), (2 * targetArrow_CircleRadius), (2 * targetArrow_CircleRadius));
            }
        }
        graphics.dispose();
    }
    
    private void drawPlayerLifepoints(){
        graphics.drawImage(CardImages.getCachedImage("images/player_lifepoints_player.png"), 50, 922, 100, 150, this);
        graphics.drawImage(CardImages.getCachedImage("images/player_lifepoints_enemy.png"), 1950, 922, 100, 150, this);
        graphics.setFont(fontPlayerLifepoints);
        graphics.setColor(Color.WHITE);
        int lifepointsPlayer = game.getPlayer(playerIndex).getLifepoints();
        int lifepointsEnemy = game.getPlayer(playerIndex).getEnemy().getLifepoints();
        graphics.drawString("" + lifepointsPlayer, 50 + ((lifepointsPlayer < 10)?26:6), 1035);
        graphics.drawString("" + lifepointsEnemy, 1950 + ((lifepointsEnemy < 10)?26:6), 1035);
    }
    
    private void drawCardDisplay(CardDisplay cardDisplay){
        graphics.translate(cardDisplay.getX(), cardDisplay.getY());
        AffineTransform tmpTransform = graphics.getTransform();
        boolean isTransformed = false;
        if(cardDisplay.getCard() instanceof FieldCard){
            FieldCard fieldCard = (FieldCard) cardDisplay.getCard();
            if(fieldCard.isTapped()){
                graphics.rotate(0.5, (CardDisplay.WIDTH / 2), (CardDisplay.HEIGHT / 2));
                isTransformed = true;
            }
            if(fieldCard.getCardPosition().getZone() == CardPosition.Zone.MONSTER){
                MonsterCard monsterCard = (MonsterCard) fieldCard;
                if((monsterCard.getOwner().getIndex() == game.getCurrentPlayerIndex()) && monsterCard.canAttack()){
                    float progress = (float) Math.sin(System.currentTimeMillis() / 300.0);
                    graphics.rotate((progress * 0.075), (CardDisplay.WIDTH / 2), (CardDisplay.HEIGHT / 2));
                    isTransformed = true;
                }
            }
        }
        drawCardDisplay(graphics, this, cardDisplay);
        if(cardDisplay.isHighlighted()){
            graphics.drawImage(CardImages.getCachedImage("images/highlight_overlay.png"), 0, 0, 300, 400, this);
        }
        if(isTransformed){
            graphics.setTransform(tmpTransform);
        }
        graphics.translate(-1 * cardDisplay.getX(), -1 * cardDisplay.getY());
    }
    
    private static int tmpX;
    private static int tmpY;
    private static LinkedList<String> tmpKeywords = new LinkedList<String>();
    public static void drawCardDisplay(Graphics2D graphics, ImageObserver imageObserver, CardDisplay cardDisplay){
        graphics = (Graphics2D) graphics.create();
        Card card = cardDisplay.getCard();
        if(cardDisplay.isFront()){
            tmpKeywords.clear();
            FieldCard fieldCard = null;
            boolean hasCastSpell = false;
            String description = "";
            if(card instanceof Land){
                tmpKeywords.add("Land");
            }
            if(card instanceof FieldCard){
                fieldCard = (FieldCard) card;
                if(fieldCard.getCastCost().isTap()){
                    tmpKeywords.add("Slow");
                }
                if(card instanceof MonsterCard){
                    MonsterCard monsterCard = (MonsterCard) card;
                    if(monsterCard.hasCharge()){
                        tmpKeywords.add("Charge");
                    }
                }
            }
            for(Mechanic mechanic : card.getMechanics()){
                if(!mechanic.getDescription().getTitle().equals(Description.DEFAULT_TITLE)){
                    tmpKeywords.add(mechanic.getDescription().getTitle());
                }
                else if(!mechanic.getDescription().getDescription().equals(Description.DEFAULT_DESCRIPTION)){
                    if(description.length() > 0){
                        description += " ";
                    }
                    description += mechanic.getDescription().getDescription();
                }
            }
            if((fieldCard != null) && (fieldCard.getCastSpell() != null)){
                tmpKeywords.add("Cast");
                hasCastSpell = true;
            }
            if(card.getCardPosition().getZone() == CardPosition.Zone.MONSTER){
                if(card.hasMechanic(Taunt.class)){
                    graphics.drawImage(CardImages.getCachedImage("images/taunt.png", 416, 415), -58, 1, imageObserver);
                }
            }
            graphics.setColor(Color.WHITE);
            graphics.fillRect(74, 43, 155, 155);
            String imageFilePath = CardImages.getCardImageFilePath(card);
            graphics.drawImage(CardImages.getCachedImage(imageFilePath, 155, 155), 74, 43, 155, 155, imageObserver);
            graphics.drawImage(getCardBackgroundImage(card.getManaTypes()), 0, 0, imageObserver);
            graphics.setFont(fontTitle);
            graphics.setColor(Color.BLACK);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            if(fontMetrics.getHeight() > 0){
                String title = card.getDescription().getTitle();
                Rectangle2D titleBounds = fontMetrics.getStringBounds(title, graphics);
                int x = 150 - (int) (titleBounds.getWidth() / 2);
                int y;
                for(int i=0;i<title.length();i++){
                    String letter = title.substring(i, i + 1);
                    Rectangle2D letterBounds = fontMetrics.getStringBounds(letter, graphics);
                    y = (int) (203 + (Math.cos((x - 47) / 46.0) * 8.3));
                    graphics.translate(x, y);
                    graphics.drawImage(getLetterImage(graphics, letter), 0, 0, null);
                    graphics.translate(-1 * x, -1 * y);
                    x += (letterBounds.getWidth() + 0.75);
                }
            }
            int startX = 60;
            int lineWidth = 184;
            tmpY = 274;
            if(tmpKeywords.size() > 0){
                String keywordsText = "";
                for(int i=0;i<tmpKeywords.size();i++){
                    if(i != 0){
                        keywordsText += " ";
                    }
                    String keyword = tmpKeywords.get(i);
                    keywordsText += keyword + (keyword.equals("Cast")?":":".");
                }
                graphics.setFont(fontKeywords);
                tmpX = startX;
                drawStringMultiLine(graphics, keywordsText, lineWidth, tmpX, startX, tmpY, -2);
                if(hasCastSpell){
                    tmpX += 3;
                    drawSpellDescription(graphics, fieldCard.getCastSpell(), lineWidth, tmpX, startX, tmpY);
                }
                tmpY += 18;
            }
            if(!card.getDescription().getDescription().equals(Description.DEFAULT_DESCRIPTION)){
                if(description.length() > 0){
                    description += " ";
                }
                description += card.getDescription().getDescription();
            }
            if(description.length() > 0){
                graphics.setFont(fontDescription);
                tmpX = startX;
                drawStringMultiLine(graphics, description, 180, tmpX, startX, tmpY, -2);
                tmpY += 18;
            }
            if(fieldCard != null){
                Spell[] spells = fieldCard.getSpells();
                for(Spell spell : spells){
                    tmpX = startX;
                    if(!spell.getCost().isEmpty()){
                        drawCost(graphics, spell.getCost(), lineWidth, tmpX, startX, tmpY);
                        tmpX += 3;
                    }
                    drawSpellDescription(graphics, spell, lineWidth, tmpX, startX, tmpY);
                    tmpY += 18;
                }
            }
            if(card.getFlavorText() != null){
                tmpX = startX;
                graphics.setFont(fontFlavorText);
                drawStringMultiLine(graphics, card.getFlavorText(), lineWidth, tmpX, startX, tmpY, -2);
                tmpY += 18;
            }
            if(card instanceof MonsterCard){
                MonsterCard monsterCard = (MonsterCard) card;
                graphics.drawImage(CardImages.getCachedImage("images/templates/stats.png"), 0, 0, 300, 400, imageObserver);
                graphics.setFont(fontStats);
                graphics.setColor(Color.WHITE);
                fontMetrics = graphics.getFontMetrics();
                String attackDamageText = ("" + monsterCard.getAttackDamage());
                Rectangle2D attackDamageBounds = fontMetrics.getStringBounds(attackDamageText, graphics);
                graphics.drawString(attackDamageText, (int) (48 - (attackDamageBounds.getWidth() / 2)), 372);
                String lifepointsText = ("" + monsterCard.getCurrentLifepoints());
                Rectangle2D lifepointsBounds = fontMetrics.getStringBounds(lifepointsText, graphics);
                tmpX = (int) (254 - (lifepointsBounds.getWidth() / 2));
                tmpY = (int) (394 - (lifepointsBounds.getHeight() / 2));
                if(monsterCard.getCurrentLifepoints() < monsterCard.getMaximumLifepoints()){
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(lifepointsText, tmpX - 1, tmpY - 1);
                    graphics.drawString(lifepointsText, tmpX + 0, tmpY - 1);
                    graphics.drawString(lifepointsText, tmpX + 1, tmpY - 1);
                    graphics.drawString(lifepointsText, tmpX - 1, tmpY + 0);
                    graphics.drawString(lifepointsText, tmpX + 0, tmpY + 0);
                    graphics.drawString(lifepointsText, tmpX + 1, tmpY + 0);
                    graphics.drawString(lifepointsText, tmpX - 1, tmpY + 1);
                    graphics.drawString(lifepointsText, tmpX + 0, tmpY + 1);
                    graphics.drawString(lifepointsText, tmpX + 1, tmpY + 1);
                    graphics.setColor(Color.RED);
                }
                graphics.drawString(lifepointsText, tmpX, tmpY);
                if(monsterCard.getTribes().length > 0){
                    String tribesText = "";
                    for(int i=0;i<monsterCard.getTribes().length;i++){
                        if(i != 0){
                            tribesText += ", ";
                        }
                        String tribeName = monsterCard.getTribes()[i].name();
                        tribesText += tribeName.substring(0, 1).toUpperCase() + tribeName.substring(1).toLowerCase();
                    }
                    graphics.setFont(fontTribes);
                    graphics.setColor(Color.BLACK);
                    fontMetrics = graphics.getFontMetrics();
                    Rectangle2D tribesBounds = fontMetrics.getStringBounds(tribesText, graphics);
                    graphics.drawString(tribesText, 150 - (int) (tribesBounds.getWidth() / 2), 357);
                }
                if(card.getCardPosition().getZone() == CardPosition.Zone.MONSTER){
                    if(monsterCard.hasMechanic(DivineShield.class)){
                        float progress = (float) ((Math.sin(System.currentTimeMillis() / 500.0) + 1) / 2);
                        graphics.setColor(new Color(1, 0.85f + (progress * 0.15f), (progress * 0.4f), 0.4f));
                        graphics.fillRoundRect(5, 15, 290, 380, 80, 80);
                    }
                    if(monsterCard.hasMechanic(Immune.class)){
                        float progress = (float) ((Math.sin(System.currentTimeMillis() / 500.0) + 1) / 2);
                        graphics.setColor(new Color((progress * 0.4f), 0.85f + (progress * 0.15f), 1, 0.4f));
                        graphics.fillRoundRect(5, 15, 290, 380, 80, 80);
                    }
                }
            }
        }
        else{
            graphics.drawImage(CardImages.getCachedImage("images/back.png"), 0, 0, 300, 400, imageObserver);
        }
        graphics.dispose();
    }
    
    private static HashMap<String, BufferedImage> cardBackgroundImages = new HashMap<String, BufferedImage>();
    public static BufferedImage getCardBackgroundImage(Mana[] manaTypes){
        String key = "";
        for(int i=0;i<manaTypes.length;i++){
            if(i != 0){
                key += ",";
            }
            key += manaTypes[i].ordinal();
        }
        BufferedImage image = cardBackgroundImages.get(key);
        if(image == null){
            int width = 300;
            int height = 400;
            int partWidth = (int) Math.round(((float) width) / manaTypes.length);
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imageGraphics = image.createGraphics();
            int x = 0;
            int lineX;
            for(Mana manaType : manaTypes){
                for(int i=(int) (-0.5f * partWidth);i<(1.5f * partWidth);i++){
                    lineX = (x + i);
                    if(lineX > 0){
                        if(lineX >= width){
                            break;
                        }
                        Image templateImage = CardImages.getCachedImage("images/templates/mana_" + manaType.ordinal() + ".png");
                        float alpha;
                        if(((i < (0.5 * partWidth)) && (lineX < (partWidth / 2)))
                        || (i > (0.5 * partWidth))){
                            alpha = 1;
                        }
                        else{
                            alpha = (1 - (Math.abs(((((float) i) / partWidth) - 0.5f))));
                        }
                        imageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                        while(!imageGraphics.drawImage(templateImage, lineX, 0, lineX + 1, height, lineX, 0, lineX + 1, height, null)){
                            //http://stackoverflow.com/questions/20442295/drawimage-wont-work-but-drawrect-does
                        }
                    }
                }
                x += partWidth;
            }
            imageGraphics.dispose();
            cardBackgroundImages.put(key, image);
        }
        return image;
    }
    
    private static void drawSpellDescription(Graphics2D graphics, Spell spell, int lineWidth, int startX, int followingX, int y){
        graphics.setFont(fontEffects);
        String text = "";
        String description = spell.getDescription().getDescription();
        if(!description.equals(Description.DEFAULT_DESCRIPTION)){
            text += description;
        }
        ManaSpell manaSpell = null;
        if(spell instanceof ManaSpell){
            manaSpell = (ManaSpell) spell;
            if(text.length() > 0){
                text += " ";
            }
            text += "Gain";
        }
        drawStringMultiLine(graphics, text, lineWidth, startX, followingX, y, -2);
        if(manaSpell != null){
            tmpX += 3;
            drawManaAmount(graphics, manaSpell.getGainedManaAmount(), lineWidth, tmpX, followingX, tmpY);
            tmpX += 1;
            graphics.drawString(".", tmpX, tmpY);
        }
    }
    
    private static final int effectsIconSize = 15;
    private static final int effectsGapSize = 2;
    private static void drawCost(Graphics2D graphics, Cost cost, int lineWidth, int startX, int followingX, int y){
        if(cost.isTap()){
            drawTapIcon(graphics, startX, y);
        }
        drawManaAmount(graphics, cost.getMana(), lineWidth, tmpX, followingX, y);
    }
    
    private static void drawTapIcon(Graphics2D graphics, int x, int y){
        graphics.drawImage(CardImages.getCachedImage("images/tap.png", effectsIconSize, effectsIconSize), x, y - 12, effectsIconSize, effectsIconSize, null);
        x += (effectsIconSize + effectsGapSize);
        tmpX = x;
    }
    
    private static void drawManaAmount(Graphics2D graphics, ManaAmount manaAmount, int lineWidth, int startX, int followingX, int y){
        tmpY = y;
        for(Mana mana : Mana.values()){
            int amount = manaAmount.getMana(mana);
            for(int i=0;i<amount;i++){
                if(tmpX > (followingX + lineWidth)){
                    tmpX = followingX;
                    tmpY += 18;
                }
                graphics.drawImage(CardImages.getCachedImage("images/mana/" + mana.ordinal() + ".png", effectsIconSize, effectsIconSize), tmpX, tmpY - 12, effectsIconSize, effectsIconSize, null);
                tmpX += (effectsIconSize + effectsGapSize);
            }
        }
    }
    
    //http://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring
    public static void drawStringMultiLine(Graphics2D graphics, String text, int lineWidth, int startX, int followingX, int y, int linesGap){
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = startX;
        String currentLine = text;
        if(fontMetrics.stringWidth(currentLine) < (lineWidth - (x - followingX))){
            graphics.drawString(currentLine, x, y);
        }
        else{
            String[] words = text.split(" ");
            currentLine = words[0];
            for(int i=1;i<words.length;i++){
                if(fontMetrics.stringWidth(currentLine + words[i]) < (lineWidth - (x - followingX))){
                    currentLine += " " + words[i];
                }
                else{
                    graphics.drawString(currentLine, x, y);
                    y += (fontMetrics.getHeight() + linesGap);
                    x = followingX;
                    currentLine = words[i];
                }
            }
            if(currentLine.trim().length() > 0){
                graphics.drawString(currentLine, x, y);
            }
        }
        x += fontMetrics.stringWidth(currentLine);
        tmpX = x;
        tmpY = y;
    }
    
    private static HashMap<String, BufferedImage> letterImages = new HashMap<String, BufferedImage>();
    private static BufferedImage getLetterImage(Graphics graphics, String letter){
        BufferedImage image = letterImages.get(letter);
        if(image == null){
            image = createStringImage(graphics, letter);
            letterImages.put(letter, image);
        }
        return image;
    }
    
    //http://stackoverflow.com/questions/10388118/how-to-make-rotated-text-look-good-with-java2d
    public static BufferedImage createStringImage(Graphics graphics, String text){
        int width = (graphics.getFontMetrics().stringWidth(text) + 5);
        int height = graphics.getFontMetrics().getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        imageGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.setFont(graphics.getFont());
        imageGraphics.drawString(text, 0, (height - graphics.getFontMetrics().getDescent()));
        imageGraphics.dispose();
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        scale = Math.min((((double) getWidth()) / GUI_WIDTH), (((double) getHeight()) / GUI_HEIGHT));
        offsetX = ((getWidth() - (scale * GUI_WIDTH)) / 2);
        offsetY = ((getHeight() - (scale * GUI_HEIGHT)) / 2);
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
