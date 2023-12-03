package AdventureModel;

public class Suspect {

    private String name;
    private boolean isMurderer;
    private int age;
    private String relation;
    private String description;
    private String background;
    private String motive;

    Suspect(String name, boolean isMurderer, int age, String relation, String description, String background, String motive) {
        this.name = name;
        this.isMurderer = isMurderer;
        this.age = age;
        this.relation = relation;
        this.description = description;
        this.background = background;
        this.motive = motive;

    }

    public String getName() {
        return name;
    }

    public boolean isMurderer() {
        return isMurderer;
    }

    public int getAge() {
        return age;
    }

    public String getRelation() {
        return relation;
    }

    public String getDescription() {
        return description;
    }

    public String getBackground() {
        return background;
    }

    public String getMotive() {
        return motive;
    }
}
