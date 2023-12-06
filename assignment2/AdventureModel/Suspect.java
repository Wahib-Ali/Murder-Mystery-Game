package AdventureModel;

/**
 * Class Suspect
 *
 * Simulates a suspect in the murder mystery game
 */
public class Suspect {

    private String name;
    private boolean isMurderer;
    private int age;
    private String relation;
    private String description;
    private String background;
    private String motive;

    /**
     * Suspect Constructor
     * ___________________________
     * Initializes necessary attributes
     *
     * @param name the name of the suspect
     * @param isMurderer true if the suspect is the Murderer. False otherwise
     * @param age the age of the suspect in years
     * @param relation the relationship this suspect has to the victim
     * @param description a descriptive passage about the suspect's appearence
     * @param background a descriptive passage about the suspect's background
     * @param motive potential motives\reasoning for this suspect to kill the victim
     */
    Suspect(String name, boolean isMurderer, int age, String relation, String description, String background, String motive) {
        this.name = name;
        this.isMurderer = isMurderer;
        this.age = age;
        this.relation = relation;
        this.description = description;
        this.background = background;
        this.motive = motive;

    }

    /**
     * getName
     * ___________________________
     * Getter method for the <getName> attribute
     *
     * @return the name of the suspect
     */
    public String getName() {
        return name;
    }

    /**
     * isMurderer
     * ___________________________
     * Boolean check for the <isMurderer> attribute
     *
     * @return True if the suspect is the murderer. False otherwise.
     */
    public boolean isMurderer() {
        return isMurderer;
    }

    /**
     * getAge
     * ___________________________
     * Getter method for the <age> attribute.
     *
     * @return the age of the suspect in years.
     */
    public int getAge() {
        return age;
    }

    /* getRelation
     * ___________________________
     * Getter method for the <relation> attribute.
     *
     * @return the relationship the suspect has to the victim as a string.
     */
    public String getRelation() {
        return relation;
    }

    /**
     * getDescription
     * ___________________________
     * Getter method for the <description> attribute.
     *
     * @return the description of the suspect as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * getBackground
     * ___________________________
     * Getter method for the <background> attribute.
     *
     * @return the background of the suspect as a string.
     */
    public String getBackground() {
        return background;
    }

    /**
     * getMotive
     * ___________________________
     * Getter method for the <motive> attribute.
     *
     * @return the potential motive of the suspect as a string.
     */
    public String getMotive() {
        return motive;
    }
}
