/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

/**
 *
 * @author Carl
 */
public class Description{

    public Description(){
        this(DEFAULT_TITLE);
    }
    
    public Description(String title){
        this(title, DEFAULT_DESCRIPTION);
    }

    public Description(String title, String description){
        this.title = title;
        this.description = description;
    }
    public static final String DEFAULT_TITLE = "<Unnamed>";
    public static final String DEFAULT_DESCRIPTION = "No description available.";
    private String title;
    private String description;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }
}
