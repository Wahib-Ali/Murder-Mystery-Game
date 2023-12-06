package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

/**
 * This class keeps track of the props or the clues in the game.
 * These clues have a name, description, and location in the game.
 * The player with the clues can pick or drop them as they like and
 * these clues can be used to pass certain passages in the game.
 */
public class AdventureClue implements Serializable {
    /**
     * The name of the clue.
     */
    private String clueName;

    /**
     * The description of the clue.
     */
    private String description;

    /**
     * The location of the clue.
     */
    private Room location = null;

    /**
     * Adventure clue Constructor
     * ___________________________
     * This constructor sets the name, description, and location of the clue.
     *
     * @param name The name of the clue in the game.
     * @param description One line description of the clue.
     * @param location The location of the clue in the game.
     */
    public AdventureClue(String name, String description, Room location){
        this.clueName = name;
        this.description = description;
        this.location = location;
    }

    /**
     * Getter method for the name attribute.
     *
     * @return name of the clue
     */
    public String getName(){
        return this.clueName;
    }

    /**
     * Getter method for the description attribute.
     *
     * @return description of the clue
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * This method returns the location of the clue if the clue is still in
     * the room. If the clue has been pickUp up by the player, it returns null.
     *
     * @return returns the location of the clue if the clue is still in
     * the room otherwise, returns null.
     */
    public Room getLocation(){
        return this.location;
    }

}
