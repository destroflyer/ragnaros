/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.client;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import ragnaros.core.Util;
import ragnaros.core.files.FileManager;
import ragnaros.game.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *
 * @author Carl
 */
public class DeckFileHandler{
    
    private static final String CARDS_SEPARATOR = ",";
    public static String FILE_EXTENSION = "xml";
    public static FileFilter FILE_FILTER = new FileFilter(){

        @Override
        public boolean accept(File file){
            return (file.isDirectory() || file.getPath().toLowerCase().endsWith("." + FILE_EXTENSION));
        }

        @Override
        public String getDescription(){
            return "Deck file (*." + FILE_EXTENSION + ")";
        }
    };
    
    public static void save(Deck deck, File file){
        try{
            Element root = new Element("deck");
            root.setAttribute("author", System.getProperty("user.name"));
            root.setAttribute("date", "" + System.currentTimeMillis());
            Document document = new Document(root);
            Element elementDescription = new Element("description");
            elementDescription.setAttribute("title", deck.getDescription().getTitle());
            elementDescription.setAttribute("description", deck.getDescription().getDescription());
            root.addContent(elementDescription);
            Element elementCards = new Element("cards");
            elementCards.setText(getCardsText(deck));
            root.addContent(elementCards);
            FileManager.putFileContent(file.getPath(), new XMLOutputter().outputString(document));
        }catch(Exception ex){
            System.out.println("Error while saving the map: " + ex.toString());
        }
    }
    
    private static String getCardsText(Cards cards){
        String text = "";
        for(int i=0;i<cards.size();i++){
            Card card = cards.get(i);
            if(i != 0){
                text += CARDS_SEPARATOR;
            }
            text += CardTypeManager.getType(card);
        }
        return text;
    }

    public static Deck load(File file){
        try{
            Deck deck = load(new SAXBuilder().build(file));
            return deck;
        }catch(Exception ex){
            System.out.println("Error while loading the deck: " + ex.toString());
        }
        return null;
    }

    private static Deck load(Document document){
        try{
            Element root = document.getRootElement();
            Deck deck = new Deck();
            Element elementDescription = root.getChild("description");
            deck.setDescription(new Description(elementDescription.getAttributeValue("title"), elementDescription.getAttributeValue("description")));
            Element elementCards = root.getChild("cards");
            String cardsText = elementCards.getText();
            if(cardsText.length() > 0){
                int[] cardTypes = Util.parseToIntArray(cardsText.split(CARDS_SEPARATOR));
                for(int cardType : cardTypes){
                    Card card = CardTypeManager.createCard(cardType);
                    deck.add(card);
                }
            }
            return deck;
        }catch(Exception ex){
            System.out.println("Error while loading the deck: " + ex.toString());
        }
        return null;
    }
}
