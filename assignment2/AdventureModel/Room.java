package AdventureModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the information about a 
 * room in the Adventure Game.
 */
public class Room implements Serializable {

    private final String adventureName;
    /**
     * The number of the room.
     */
    private int roomNumber;

    /**
     * The name of the room.
     */
    private String roomName;

    /**
     * The description of the room.
     */
    private String roomDescription;

    /**
     * The passage table for the room.
     */
    private PassageTable motionTable = new PassageTable();

    /**
     * The list of objects in the room.
     */
    public ArrayList<AdventureObject> objectsInRoom = new ArrayList<AdventureObject>();

    public ArrayList<AdventureClue> cluesInRoom = new ArrayList<AdventureClue>();

    /**
     * A boolean to store if the room has been visited or not
     */
    private boolean isVisited;

    /**
     * AdvGameRoom constructor.
     *
     * @param roomName: The name of the room.
     * @param roomNumber: The number of the room.
     * @param roomDescription: The description of the room.
     */
    public Room(String roomName, int roomNumber, String roomDescription, String adventureName){
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
        this.adventureName = adventureName;
        this.isVisited = false;
    }


    /**
     * Returns a comma delimited list of every
     * object's description that is in the given room,
     * e.g. "a can of tuna, a beagle, a lamp".
     *
     * @return delimited string of object descriptions
     */
    public String getObjectString() {
        // Create a list of all the objects in the room and then move everything to a string
        List<String> lst = new ArrayList<>();
        String final_str = "";

        for (int i = 0; i < this.objectsInRoom.size(); i++){
            lst.add(this.objectsInRoom.get(i).getDescription());
        }

        for (int i = 0; i < lst.size(); i++){
            if (i == lst.size() - 1){
                final_str += lst.get(i);
            } else{
                final_str += lst.get(i) + ",";
            }
        }

        return final_str;
    }

    public String getClueString() {
        // Create a list of all the objects in the room and then move everything to a string
        List<String> lst = new ArrayList<>();
        String final_str = "";

        for (int i = 0; i < this.cluesInRoom.size(); i++){
            lst.add(this.cluesInRoom.get(i).getDescription());
        }

        for (int i = 0; i < lst.size(); i++){
            if (i == lst.size() - 1){
                final_str += lst.get(i);
            } else{
                final_str += lst.get(i) + ",";
            }
        }

        return final_str;
    }

    /**
     * Returns a comma delimited list of every
     * move that is possible from the given room,
     * e.g. "DOWN, UP, NORTH, SOUTH".
     *
     * @return delimited string of possible moves
     */
    public String getCommands() {
        // Create a list of all the directions and then move everything to a string
        List<String> lst = new ArrayList<>();
        String final_str = "";

        for (int i = 0; i < this.getMotionTable().passageTable.size(); i++){
            lst.add(this.getMotionTable().passageTable.get(i).getDirection());
        }

        for (int i = 0; i < lst.size(); i++){
            if (i == lst.size() - 1){
                final_str += lst.get(i);
            } else{
                final_str += lst.get(i) + ",";
            }
        }

        return final_str;
    }

    /**
     * This method adds a game object to the room.
     *
     * @param object to be added to the room.
     */
    public void addGameObject(AdventureObject object){
        this.objectsInRoom.add(object);
    }

    public void addGameClue(AdventureClue clue){
        this.cluesInRoom.add(clue);
    }

    /**
     * This method removes a game object from the room.
     *
     * @param object to be removed from the room.
     */
    public void removeGameObject(AdventureObject object){
        this.objectsInRoom.remove(object);
    }

    public void removeGameClue(AdventureClue clue){
        this.cluesInRoom.remove(clue);
    }


    /**
     * This method checks if an object is in the room.
     *
     * @param objectName Name of the object to be checked.
     * @return true if the object is present in the room, false otherwise.
     */
    public boolean checkIfObjectInRoom(String objectName){
        for(int i = 0; i<objectsInRoom.size();i++){
            if(this.objectsInRoom.get(i).getName().equals(objectName)) return true;
        }
        return false;
    }

    public boolean checkIfClueInRoom(String clueName){
        for(int i = 0; i<cluesInRoom.size();i++){
            if(this.cluesInRoom.get(i).getName().equals(clueName)) return true;
        }
        return false;
    }

    /**
     * Sets the visit status of the room to true.
     */
    public void visit(){
        isVisited = true;
    }

    /**
     * Getter for returning an AdventureObject with a given name
     *
     * @param objectName: Object name to find in the room
     * @return: AdventureObject
     */
    public AdventureObject getObject(String objectName){
        for(int i = 0; i<objectsInRoom.size();i++){
            if(this.objectsInRoom.get(i).getName().equals(objectName)) return this.objectsInRoom.get(i);
        }
        return null;
    }

    public AdventureClue getClue (String clueName){
        for(int i = 0; i<cluesInRoom.size();i++){
            if(this.cluesInRoom.get(i).getName().equals(clueName)) return this.cluesInRoom.get(i);
        }
        return null;
    }

    /**
     * Getter method for the number attribute.
     *
     * @return: number of the room
     */
    public int getRoomNumber(){
        return this.roomNumber;
    }

    /**
     * Getter method for the description attribute.
     *
     * @return: description of the room
     */
    public String getRoomDescription(){
        return this.roomDescription.replace("\n", " ");
    }


    /**
     * Getter method for the name attribute.
     *
     * @return: name of the room
     */
    public String getRoomName(){
        return this.roomName;
    }


    /**
     * Getter method for the visit attribute.
     *
     * @return: visit status of the room
     */
    public boolean getVisited(){
        return this.isVisited;
    }


    /**
     * Getter method for the motionTable attribute.
     *
     * @return: motion table of the room
     */
    public PassageTable getMotionTable(){
        return this.motionTable;
    }


}