package views;

import AdventureModel.Suspect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class SuspectView
 *
 * This class visualizes all suspects in the game and displays information about each suspect.
 */
public class SuspectView {

    //FLOATING POINT ON EXAM
    Button leftArrowButton, rightArrowButton, chooseButton, goBack;
    AdventureGameView adventureGameView;
    GridPane gridPane;
    Stage stage;
    Suspect currentSuspect;
    int currentSuspectNum = 1;
    int colourInvert;
    Label instructionLabel = new Label("Choose The Correct Suspect");
    Label suspectDescLabel = new Label("");
    ImageView suspectImageView;



    /**
     * SuspectView Constructor:
     * __________________________
     *
     * Initializes required attributes.
     *
     * @param adventureGameView: The AdventureGameView associated with this SuspectView.
     */
    SuspectView (AdventureGameView adventureGameView, int colour) {

        this.adventureGameView = adventureGameView;
        this.stage = adventureGameView.stage;
        this.gridPane = adventureGameView.gridPane;
        this.colourInvert = colour;

        if (colour == 0){
            intiUI();
        } else{
            intiUI1();
        }

    }

    /**
     * intiUI
     * __________________________
     * Initializes the GUI of SuspectView with normal colours.
     */
    public void intiUI() {

        this.stage.setTitle("Suspect Screen");
        this.gridPane.getChildren().clear();

        // GridPane, anyone?
        gridPane.setPadding(new Insets(20));
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#000000"),
                new CornerRadii(0),
                new Insets(0)
        )));

        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(650);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
        column1.setHgrow( Priority.SOMETIMES );

        // Row constraints
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints( 550 );
        RowConstraints row3 = new RowConstraints();
        row1.setVgrow( Priority.SOMETIMES );
        row3.setVgrow( Priority.SOMETIMES );

        gridPane.getColumnConstraints().addAll( column1 , column2 , column1 );
        gridPane.getRowConstraints().addAll( row1 , row2 , row1 );

        // Buttons



        leftArrowButton = new Button("←");
        leftArrowButton.setTextAlignment(TextAlignment.CENTER);
        leftArrowButton.setId("Left Pointing Arrow");
        customizeButton(leftArrowButton, 150, 120, true);
        AdventureGameView.makeButtonAccessible(leftArrowButton, "Left Arrow Button", "This button navigates to the previous suspect.", "This button navigates to the previous suspect. Click on it to view other suspects.");
        addLeftNavigateEvent();

        rightArrowButton = new Button("→");
        rightArrowButton.setTextAlignment(TextAlignment.CENTER);
        rightArrowButton.setId("Right Pointing Arrow");
        customizeButton(rightArrowButton, 150, 120, true);
        AdventureGameView.makeButtonAccessible(rightArrowButton, "Right Arrow Button", "This button navigates to the next suspect.", "This button navigates to the next suspect. Click on it to view other suspects.");
        addRightNavigateEvent();

        chooseButton = new Button("Choose Suspect");
        chooseButton.setTextAlignment(TextAlignment.CENTER);
//        chooseButton.setAlignment(Pos.CENTER);
        chooseButton.setId("Choose Suspect");
        customizeButton(chooseButton, 300, 75, false);
        AdventureGameView.makeButtonAccessible(chooseButton, "Choose Button", "Finalize your selection with this button.", "This button finalizes your selection. Press enter to proceed, or use the other buttons to select a different suspect.");
        addChooseButtonEvent();

        goBack = new Button("Go Back");
        goBack.setTextAlignment(TextAlignment.CENTER);
        goBack.setId("Go Back");
        customizeButton(goBack, 200, 50, false);
        AdventureGameView.makeButtonAccessible(goBack, "Go Back", "Press this button to return to the game.", "Press this button to return to the game. You can also choose a suspect instead if youre confident in your choice");
        addGoBackEvent();

//        //labels for inventory and room items

        instructionLabel.setAlignment(Pos.CENTER);
        instructionLabel.setStyle("-fx-text-fill: white;");
        instructionLabel.setFont(new Font("Arial", 20)); //FIXME: ADD COLOUR INVERSION COMPATIBILITY. USE colourInvert (bool).

        suspectDescLabel.setAlignment(Pos.CENTER);
        suspectDescLabel.setStyle("-fx-text-fill: white;");
        suspectDescLabel.setFont(new Font("Arial", 16)); //FIXME: ADD COLOUR INVERSION COMPATIBILITY. USE colourInvert (bool).


        gridPane.add(nodeCenter(new Node[] {instructionLabel, goBack}), 1, 0);

        gridPane.add(nodeCenter(new Button[] {leftArrowButton}), 0, 1);
        gridPane.add(nodeCenter(new Button[] {rightArrowButton}), 2, 1);
        gridPane.add(nodeCenter(new Button[] {chooseButton}), 1, 2);


        updateScene(""); //method displays an image and whatever text is supplied
    }

    /**
     * intiUI
     * __________________________
     * Initializes the GUI of SuspectView with inverted colours.
     */
    public void intiUI1() {

        this.stage.setTitle("Suspect Screen");
        this.gridPane.getChildren().clear();


        // GridPane, anyone?
        gridPane.setPadding(new Insets(20));
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));

        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(650);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
        column1.setHgrow( Priority.SOMETIMES );

        // Row constraints
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints( 550 );
        RowConstraints row3 = new RowConstraints();
        row1.setVgrow( Priority.SOMETIMES );
        row3.setVgrow( Priority.SOMETIMES );

        gridPane.getColumnConstraints().addAll( column1 , column2 , column1 );
        gridPane.getRowConstraints().addAll( row1 , row2 , row1 );

        // Buttons



        leftArrowButton = new Button("←");
        leftArrowButton.setTextAlignment(TextAlignment.CENTER);
        leftArrowButton.setId("Left Pointing Arrow");
        customizeButton1(leftArrowButton, 150, 120, true);
        AdventureGameView.makeButtonAccessible(leftArrowButton, "Left Arrow Button", "This button navigates to the previous suspect.", "This button navigates to the previous suspect. Click on it to view other suspects.");
        addLeftNavigateEvent();

        rightArrowButton = new Button("→");
        rightArrowButton.setTextAlignment(TextAlignment.CENTER);
        rightArrowButton.setId("Right Pointing Arrow");
        customizeButton1(rightArrowButton, 150, 120, true);
        AdventureGameView.makeButtonAccessible(rightArrowButton, "Right Arrow Button", "This button navigates to the next suspect.", "This button navigates to the next suspect. Click on it to view other suspects.");
        addRightNavigateEvent();

        chooseButton = new Button("Choose Suspect");
        chooseButton.setTextAlignment(TextAlignment.CENTER);
//        chooseButton.setAlignment(Pos.CENTER);
        chooseButton.setId("Choose Suspect");
        customizeButton1(chooseButton, 300, 75, false);
        AdventureGameView.makeButtonAccessible(chooseButton, "Choose Button", "Finalize your selection with this button.", "This button finalizes your selection. Press enter to proceed, or use the other buttons to select a different suspect.");
        addChooseButtonEvent();

        goBack = new Button("Go Back");
        goBack.setTextAlignment(TextAlignment.CENTER);
        goBack.setId("Go Back");
        customizeButton1(goBack, 200, 50, false);
        AdventureGameView.makeButtonAccessible(goBack, "Go Back", "Press this button to return to the game.", "Press this button to return to the game. You can also choose a suspect instead if youre confident in your choice");
        addGoBackEvent();

//        //labels for inventory and room items

        instructionLabel.setAlignment(Pos.CENTER);
        instructionLabel.setStyle("-fx-text-fill: black;");
        instructionLabel.setFont(new Font("Arial", 20));

        suspectDescLabel.setAlignment(Pos.CENTER);
        suspectDescLabel.setStyle("-fx-text-fill: black;");
        suspectDescLabel.setFont(new Font("Arial", 16));


        gridPane.add(nodeCenter(new Node[] {instructionLabel, goBack}), 1, 0);

        gridPane.add(nodeCenter(new Button[] {leftArrowButton}), 0, 1);
        gridPane.add(nodeCenter(new Button[] {rightArrowButton}), 2, 1);
        gridPane.add(nodeCenter(new Button[] {chooseButton}), 1, 2);


        updateScene(""); //method displays an image and whatever text is supplied
    }

    /**
     * customizeButton
     * __________________________
     * This method customizes the button to the normal style of the game.
     *
     * @param inputButton the button to customize
     * @param w the width to set the button to
     * @param h the height to set the button to
     * @param isArrow true if button is an arrow button. False otherwise.
     */
    private void customizeButton(Button inputButton, int w, int h, boolean isArrow) {
        inputButton.setPrefSize(w, h);

        if (isArrow) {
            inputButton.setFont(new Font("Arial", 36));
        } else {
            inputButton.setFont(new Font("Arial", 18));
        }

        inputButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
    }

    /**
     * customizeButton1
     * __________________________
     * This method customizes the button to the inverted style of the game.
     *
     * @param inputButton the button to customize
     * @param w the width to set the button to
     * @param h the height to set the button to
     * @param isArrow true if button is an arrow button. False otherwise.
     */
    private void customizeButton1(Button inputButton, int w, int h, boolean isArrow) {
        inputButton.setPrefSize(w, h);

        if (isArrow) {
            inputButton.setFont(new Font("Arial", 36));
        } else {
            inputButton.setFont(new Font("Arial", 18));
        }

        inputButton.setStyle("-fx-background-color: #e878e4; -fx-text-fill: black;");
    }

    /**
     * updateScene
     * __________________________
     * This method updates the scene based on the which directional arrow was pressed.
     * Allows user to navigate through the suspects.
     *
     * @param direction the direction indicating which arrow button was pressed.
     */
    private void updateScene(String direction) {

        if (direction.equals("")) {
            currentSuspectNum = 1; //Shouldnt have to worry about this.
        } else if (direction.equals("LEFT")) {
            if (currentSuspectNum == 1) {
                currentSuspectNum = 4;
            } else {
                currentSuspectNum -= 1;
            }
        } else if (direction.equals("RIGHT")) {
            currentSuspectNum = (currentSuspectNum % 4) + 1;
        }

        this.currentSuspect = adventureGameView.model.getSuspects().get(currentSuspectNum);

        getRoomImage();

        suspectDescLabel.setPrefWidth(500);
        suspectDescLabel.setPrefHeight(500);
        suspectDescLabel.setTextOverrun(OverrunStyle.CLIP);
        suspectDescLabel.setWrapText(true);

        suspectDescLabel.setText("Name: " + currentSuspect.getName() + "\n\n"
                + "Age: " + currentSuspect.getAge() + "\n\n"
                + "Relation To Victim: Victim's " + currentSuspect.getRelation() + "\n\n"
                + "Physical Description: " + currentSuspect.getDescription() + "\n\n"
                + "Background: " + currentSuspect.getBackground() + "\n\n"
                + "Potential Motive: " + currentSuspect.getMotive());

        ScrollPane descriptionScroll = new ScrollPane(suspectDescLabel);
        if (colourInvert == 0){
            descriptionScroll.setStyle("-fx-background: rgb(0,0,0);\n -fx-background-color: rgb(0,0,0)");
        } else {
            descriptionScroll.setStyle("-fx-background: rgb(255,255,255);\n -fx-background-color: rgb(255,255,255)");
        }

        VBox roomPane = new VBox(suspectImageView,descriptionScroll);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        if (colourInvert == 0){
            roomPane.setStyle("-fx-background-color: #000000;");
        } else {
            roomPane.setStyle("-fx-background-color: #FFFFFF;");
        }

        gridPane.add(roomPane, 1, 1);
        stage.sizeToScene();




    }


    /**
     * addLeftNavigateEvent
     * __________________________
     * Adds an event action that occurs when <leftArrowButton> is pressed
     */
    private void addLeftNavigateEvent() {
        leftArrowButton.setOnAction(e -> {
            updateScene("LEFT");
        });
    }

    /**
     * addRightNavigateEvent
     * __________________________
     * Adds an event action that occurs when <rightArrowButton> is pressed
     */
    private void addRightNavigateEvent() {
        rightArrowButton.setOnAction(e -> {
            updateScene("RIGHT");
        });
    }

    /**
     * addChooseButtonEvent
     * __________________________
     * Adds an event action that occurs when <chooseButton> is pressed
     */
    private void addChooseButtonEvent() {
        chooseButton.setOnAction(e -> {
            EndScreenView end = new EndScreenView(this.adventureGameView, this.colourInvert);
        });
    }

    /**
     * addGoBackEvent
     * __________________________
     * Adds an event action that occurs when <goBack> is pressed
     */
    private void addGoBackEvent() {
        goBack.setOnAction(e -> {
            this.adventureGameView.gridPane.getChildren().clear();
            if (this.adventureGameView.colourInvert == 0) {
                this.adventureGameView.intiUI();
            }
            else {
                this.adventureGameView.intiUI2();
            }
        });
    }

    /**
     * getRoomImage
     * __________________________
     * getds and displays the current suspect image
     */
    private void getRoomImage() {

        String roomImage = this.adventureGameView.model.getDirectoryName() + "/suspect-images/" + currentSuspectNum + ".png";

        Image roomImageFile = new Image(roomImage);
        suspectImageView = new ImageView(roomImageFile);
        suspectImageView.setPreserveRatio(true);
        suspectImageView.setFitWidth(400);
        suspectImageView.setFitHeight(400);

        //set accessible text
        suspectImageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        suspectImageView.setAccessibleText(this.adventureGameView.model.getPlayer().getCurrentRoom().getRoomDescription());
        suspectImageView.setFocusTraversable(true);
    }

    /**
     * nodeCenter
     * __________________________
     * Return a Vbox which centers a list of nodes.
     * Used to center nodes in grid pane grids.
     *
     * @param nodes the array of nodes to center.
     * @return a Vbox, which centers the nodes in the grid pane.
     */
    private VBox nodeCenter(Node[] nodes) {
        VBox nodeCenter = new VBox();
        if (colourInvert == 0){
            nodeCenter.setStyle("-fx-background-color: #000000;");
        } else {
            nodeCenter.setStyle("-fx-background-color: #FFFFFF;");
        }
        nodeCenter.setPadding(new Insets(20, 20, 20, 20));
        nodeCenter.getChildren().addAll(nodes);
        nodeCenter.setSpacing(10);
        nodeCenter.setAlignment(Pos.CENTER);
        return nodeCenter;
    }

}
