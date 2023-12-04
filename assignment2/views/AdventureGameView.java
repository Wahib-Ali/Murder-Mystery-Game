package views;

import AdventureModel.AdventureGame;
import AdventureModel.AdventureObject;
import AdventureModel.AdventureClue;
import AdventureModel.Passage;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent; //you will need these!
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.event.EventHandler; //you will need this too!
import javafx.scene.AccessibleRole;

import javax.xml.stream.events.EndElement;
import java.io.File;
import java.util.Objects;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * Class AdventureGameView.
 *
 * This is the Class that will visualize your model.
 * You are asked to demo your visualization via a Zoom
 * recording. Place a link to your recording below.
 *
 * ZOOM LINK: https://drive.google.com/file/d/15rqSZpYpOd5PUS0MR_xkQ6Zvjd8pcTNK/view?usp=sharing
 * PASSWORD: There is no password required to access
 */
public class AdventureGameView {

    AdventureGame model; //model of the game
    Stage stage; //stage on which all is rendered
    Button saveButton, loadButton, helpButton; //buttons
    Button viewSuspects;
    Boolean helpToggle = false; //is help on display?

    GridPane gridPane = new GridPane(); //to hold images and buttons
    Label roomDescLabel = new Label(); //to hold room description and/or instructions
    VBox objectsInRoom = new VBox(); //to hold room items
    VBox objectsInInventory = new VBox(); //to hold inventory items
    ImageView roomImageView; //to hold room image
    TextField inputTextField; //for user input
    Voice voice;

    private MediaPlayer mediaPlayer; //to play audio
    private boolean mediaPlaying; //to know if the audio is playing
    private VBox instructionBox; //to showcase instructions on screen

    /**
     * Adventure Game View Constructor
     * __________________________
     * Initializes attributes
     */
    public AdventureGameView(AdventureGame model, Stage stage) {
        this.model = model;
        this.stage = stage;
        intiUI();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // setting up the stage
        this.stage.setTitle("Group 69's New Adventure Game");

        //Inventory + Room items
        objectsInInventory.setSpacing(10);
        objectsInInventory.setAlignment(Pos.TOP_CENTER);
        objectsInRoom.setSpacing(10);
        objectsInRoom.setAlignment(Pos.TOP_CENTER);

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
        saveButton = new Button("Save");
        saveButton.setId("Save");
        customizeButton(saveButton, 100, 50);
        makeButtonAccessible(saveButton, "Save Button", "This button saves the game.", "This button saves the game. Click it in order to save your current progress, so you can play more later.");
        addSaveEvent();

        loadButton = new Button("Load");
        loadButton.setId("Load");
        customizeButton(loadButton, 100, 50);
        makeButtonAccessible(loadButton, "Load Button", "This button loads a game from a file.", "This button loads the game from a file. Click it in order to load a game that you saved at a prior date.");
        addLoadEvent();

        helpButton = new Button("Instructions");
        helpButton.setId("Instructions");
        customizeButton(helpButton, 140, 50);
        makeButtonAccessible(helpButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();

        viewSuspects = new Button("Choose Suspect");
        viewSuspects.setId("Choose Suspect");
        customizeButton(viewSuspects, 140, 50);
        makeButtonAccessible(viewSuspects, "View Suspects Button", "This button allows you to navigate through the potential suspects.", "This button allows you to navigate through the potential suspects. You can also choose a suspect if you feel confident in your choice.");
        addViewSuspectsEvent();

        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(saveButton, helpButton, viewSuspects, loadButton);
        topButtons.setSpacing(10);
        topButtons.setAlignment(Pos.CENTER);

        inputTextField = new TextField();
        inputTextField.setFont(new Font("Arial", 16));
        inputTextField.setFocusTraversable(true);

        inputTextField.setAccessibleRole(AccessibleRole.TEXT_AREA);
        inputTextField.setAccessibleRoleDescription("Text Entry Box");
        inputTextField.setAccessibleText("Enter commands in this box.");
        inputTextField.setAccessibleHelp("This is the area in which you can enter commands you would like to play.  Enter a command and hit return to continue.");
        addTextHandlingEvent(); //attach an event to this input field

        //labels for inventory and room items
        Label objLabel =  new Label("Objects in Room");
        objLabel.setAlignment(Pos.CENTER);
        objLabel.setStyle("-fx-text-fill: white;");
        objLabel.setFont(new Font("Arial", 16));

        Label invLabel =  new Label("Your Inventory");
        invLabel.setAlignment(Pos.CENTER);
        invLabel.setStyle("-fx-text-fill: white;");
        invLabel.setFont(new Font("Arial", 16));

        //add all the widgets to the GridPane
        gridPane.add( objLabel, 0, 0, 1, 1 );  // Add label
        gridPane.add( topButtons, 1, 0, 1, 1 );  // Add buttons
        gridPane.add( invLabel, 2, 0, 1, 1 );  // Add label

        Label commandLabel = new Label("What would you like to do?");
        commandLabel.setStyle("-fx-text-fill: white;");
        commandLabel.setFont(new Font("Arial", 16));

        updateScene(""); //method displays an image and whatever text is supplied
        updateItems(); //update items shows inventory and objects in rooms

        // adding the text area and submit button to a VBox
        VBox textEntry = new VBox();
        textEntry.setStyle("-fx-background-color: #000000;");
        textEntry.setPadding(new Insets(20, 20, 20, 20));
        textEntry.getChildren().addAll(commandLabel, inputTextField);
        textEntry.setSpacing(10);
        textEntry.setAlignment(Pos.CENTER);
        gridPane.add( textEntry, 0, 2, 3, 1 );

        // add timer
        time timee = new time(10, 0);
        Label timer = new Label();
        gridPane.add(timer, 1, 0);
        timer.setStyle("-fx-text-fill: white;");
        timer.setFont(new Font("Arial", 30));
        timer.setText(timee.getCurrentTime());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            if(timee.getCurrentTime().equals("0:0")){
                                gridPane.requestFocus();
                                EndScreenView endscreen = new EndScreenView(this);
                            }
                            timee.oneSecondPassed();
                            timer.setText(timee.getCurrentTime());
                        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //text-to-speech button
        Button tts = new Button();
        customizeButton(tts, 140, 50);
        tts.setText("Articulate Desc");
        makeButtonAccessible(tts, "Articulate Room Description Button", "Read room description.", "Run a text-to-speech sound to read the room description.");
        tts.setOnAction(e -> {articulateRoomDescription();});
        topButtons.getChildren().add(tts);

        // text-to-speech setup
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        this.voice = vm.getVoice("kevin16");
        this.voice.allocate();

        // Render everything
        var scene = new Scene( gridPane ,  1000, 800);
        scene.setFill(Color.BLACK);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();

    }


    /**
     * makeButtonAccessible
     * __________________________
     * For information about ARIA standards, see
     * https://www.w3.org/WAI/standards-guidelines/aria/
     *
     * @param inputButton the button to add screenreader hooks to
     * @param name ARIA name
     * @param shortString ARIA accessible text
     * @param longString ARIA accessible help text
     */
    public static void makeButtonAccessible(Button inputButton, String name, String shortString, String longString) {
        inputButton.setAccessibleRole(AccessibleRole.BUTTON);
        inputButton.setAccessibleRoleDescription(name);
        inputButton.setAccessibleText(shortString);
        inputButton.setAccessibleHelp(longString);
        inputButton.setFocusTraversable(true);
    }

    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w width
     * @param h height
     */
    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", 16));
        inputButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
    }

    /**
     * addTextHandlingEvent
     * __________________________
     * Add an event handler to the myTextField attribute 
     *
     * Your event handler should respond when users 
     * hits the ENTER or TAB KEY. If the user hits 
     * the ENTER Key, strip white space from the
     * input to inputTextField and pass the stripped
     * string to submitEvent for processing.
     *
     * If the user hits the TAB key, move the focus 
     * of the scene onto any other node in the scene 
     * graph by invoking requestFocus method.
     */
    private void addTextHandlingEvent() {
        // Implementing an event handler

        inputTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent keyEvent) {
                // Check whether the event is 'enter' or 'tab'
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    // Obtain the text the user inputted
                    String text = inputTextField.getText();

                    // Pass the stripped text to submitEvent
                    text = text.strip();
                    submitEvent(text);

                    // Clear the users input
                    inputTextField.clear();

                } else if (keyEvent.getCode() == KeyCode.TAB) {
                    // Move focus onto something else
                    gridPane.requestFocus();
                }
            }
        });

    }


    /**
     * submitEvent
     * __________________________
     *
     * @param text the command that needs to be processed
     */
    private void submitEvent(String text) {

        text = text.strip(); //get rid of white space
        stopArticulation(); //if speaking, stop

        if (text.equalsIgnoreCase("CHOOSE")) {
            SuspectView suspectView = new SuspectView(this);
        }

        if (text.equalsIgnoreCase("HELP") || text.equalsIgnoreCase("H")) {
            showInstructions();
            return;
        } else if (text.equalsIgnoreCase("COMMANDS") || text.equalsIgnoreCase("C")) {
            showCommands(); //this is new!  We did not have this command in A1
            return;
        } else if(text.equalsIgnoreCase("GUESS")){
        gridPane.requestFocus();
        EndScreenView endscreen = new EndScreenView(this);
        return;
    }

        //try to move!
        String output = this.model.interpretAction(text); //process the command!

        if (output == null || (!output.equals("GAME OVER") && !output.equals("FORCED") && !output.equals("HELP"))) {
            updateScene(output);
            updateItems();
        } else if (output.equals("GAME OVER")) {
            updateScene("");
            updateItems();
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        } else if (output.equals("FORCED")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            String forcedEvent = "FORCED";

            // Check if the possible rooms have forced options
            if (model.getPlayer().getCurrentRoom().getMotionTable().optionExists(forcedEvent)) {

                updateItems();
                updateScene("");

                pause.setOnFinished(event -> {
                    // Check for forced statements again in the next rooms
                    submitEvent(forcedEvent);
                });
                pause.play();
            }
        }
    }


    /**
     * showCommands
     * __________________________
     *
     * update the text in the GUI (within roomDescLabel)
     * to show all the moves that are possible from the 
     * current room.
     */
    private void showCommands() {
        // Set commands of current room in a Label
        String roomCommands = "You can move in these directions:" + "\n"  + "\n" + this.model.player.getCurrentRoom().getCommands();
        Label roomCommandsLabel = new Label();
        roomCommandsLabel.setText(roomCommands);
        roomCommandsLabel.setTextFill(Color.color(1,1,1));

        // Remove the current VBox from the GUI
        VBox oldRoomPane = new VBox(roomImageView,roomDescLabel);
        gridPane.getChildren().remove(oldRoomPane);

        // Create a new VBox including the image and commands and then add to the GUI
        VBox roomPane = new VBox(roomImageView,roomCommandsLabel);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        roomPane.setStyle("-fx-background-color: #000000;");

        gridPane.add(roomPane, 1, 1);
    }


    /**
     * updateScene
     * __________________________
     *
     * Show the current room, and print some text below it.
     * If the input parameter is not null, it will be displayed
     * below the image.
     * Otherwise, the current room description will be dispplayed
     * below the image.
     * 
     * @param textToDisplay the text to display below the image.
     */
    public void updateScene(String textToDisplay) {

        getRoomImage(); //get the image of the current room
        formatText(textToDisplay); //format the text to display
        roomDescLabel.setPrefWidth(500);
        roomDescLabel.setPrefHeight(500);
        roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
        roomDescLabel.setWrapText(true);
        VBox roomPane = new VBox(roomImageView,roomDescLabel);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        roomPane.setStyle("-fx-background-color: #000000;");

        gridPane.add(roomPane, 1, 1);
        stage.sizeToScene();

        //finally, articulate the description
        // if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();
    }

    /**
     * formatText
     * __________________________
     *
     * Format text for display.
     * 
     * @param textToDisplay the text to be formatted for display.
     */
    private void formatText(String textToDisplay) {
        if (textToDisplay == null || textToDisplay.isBlank()) {
            String roomDesc = this.model.getPlayer().getCurrentRoom().getRoomDescription() + "\n";
            String objectString = this.model.getPlayer().getCurrentRoom().getObjectString();
            String clueString = this.model.getPlayer().getCurrentRoom().getClueString();
            if (objectString != null && !objectString.isEmpty() && clueString != null && !clueString.isEmpty()) roomDescLabel.setText(roomDesc + "\n\nObjects and clues in this room:\n" + objectString + "\n" + clueString);
            else if (objectString != null && !objectString.isEmpty()) roomDescLabel.setText(roomDesc + "\n\nObjects in this room:\n" + objectString);
            else if (clueString != null && !clueString.isEmpty()) roomDescLabel.setText(roomDesc + "\n\nClues in this room:\n" + clueString);
            else roomDescLabel.setText(roomDesc);
        } else roomDescLabel.setText(textToDisplay);
        roomDescLabel.setStyle("-fx-text-fill: white;");
        roomDescLabel.setFont(new Font("Arial", 16));
        roomDescLabel.setAlignment(Pos.CENTER);
    }

    /**
     * getRoomImage
     * __________________________
     *
     * Get the image for the current room and place 
     * it in the roomImageView 
     */
    private void getRoomImage() {

        int roomNumber = this.model.getPlayer().getCurrentRoom().getRoomNumber();
        String roomImage = this.model.getDirectoryName() + "/room-images/" + roomNumber + ".png";

        Image roomImageFile = new Image(roomImage);
        roomImageView = new ImageView(roomImageFile);
        roomImageView.setPreserveRatio(true);
        roomImageView.setFitWidth(400);
        roomImageView.setFitHeight(400);

        //set accessible text
        roomImageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        roomImageView.setAccessibleText(this.model.getPlayer().getCurrentRoom().getRoomDescription());
        roomImageView.setFocusTraversable(true);
    }

    /**
     * updateItems
     * __________________________
     *
     * This method is partially completed, but you are asked to finish it off.
     *
     * The method should populate the objectsInRoom and objectsInInventory Vboxes.
     * Each Vbox should contain a collection of nodes (Buttons, ImageViews, you can decide)
     * Each node represents a different object.
     * 
     * Images of each object are in the assets 
     * folders of the given adventure game.
     */
    public void updateItems() {

        // Empty the two VBOX inventory's since they're going to be added back in the for loops
        objectsInRoom.getChildren().clear();

        //write some code here to add images of objects in a given room to the objectsInRoom Vbox
        for (AdventureObject item : this.model.getPlayer().getCurrentRoom().objectsInRoom) {

            Button itemButton = new Button();
            itemButton.setAccessibleRole(AccessibleRole.BUTTON);
            itemButton.setAccessibleRoleDescription("Room Item.");
            itemButton.setAccessibleText(item.getDescription());
            itemButton.setOnAction(e -> {
                this.model.getPlayer().takeObject(item.getName());
                updateItems();
                objectsInRoom.getChildren().remove(itemButton);
            });

            // Now load the image
            String imagePath = model.getDirectoryName() + "/objectImages/" + item.getName() + ".jpg";
            Image itemImage = new Image(new File(imagePath).toURI().toString());
            ImageView imageView = new ImageView(itemImage);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            // Add the name of the object under its photo
            Label imageLabel = new Label(item.getName());
            imageLabel.setStyle("-fx-text-fill: black;");
            imageLabel.setWrapText(true);
            imageLabel.setPrefSize(100,10);
            VBox imageBox = new VBox();
            imageBox.getChildren().addAll(imageView, imageLabel);
            imageBox.setAlignment(Pos.TOP_CENTER);

            itemButton.setGraphic(imageBox);

            objectsInRoom.getChildren().add(itemButton);
        }

        // implement the same thing above but for the clues but still append into the objectsInInventory and InRoom VBox
        for (AdventureClue item : this.model.getPlayer().getCurrentRoom().cluesInRoom) {

            Button itemButton = new Button();
            itemButton.setAccessibleRole(AccessibleRole.BUTTON);
            itemButton.setAccessibleRoleDescription("Room Clue.");
            itemButton.setAccessibleText(item.getDescription());
            itemButton.setOnAction(e -> {
                this.model.getPlayer().takeClue(item.getName());
                updateItems();
                objectsInRoom.getChildren().remove(itemButton);
            });

            // Now load the image
            String imagePath = model.getDirectoryName() + "/clueImages/" + item.getName() + ".jpg";
            Image itemImage = new Image(new File(imagePath).toURI().toString());
            ImageView imageView = new ImageView(itemImage);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            // Add the name of the object under its photo
            Label imageLabel = new Label(item.getName());
            imageLabel.setStyle("-fx-text-fill: black;");
            imageLabel.setWrapText(true);
            imageLabel.setPrefSize(100,10);
            VBox imageBox = new VBox();
            imageBox.getChildren().addAll(imageView, imageLabel);
            imageBox.setAlignment(Pos.TOP_CENTER);

            itemButton.setGraphic(imageBox);

            objectsInRoom.getChildren().add(itemButton);
        }


        objectsInInventory.getChildren().clear();

        //write some code here to add images of objects in a player's inventory room to the objectsInInventory Vbox
        //please use setAccessibleText to add "alt" descriptions to your images!
        for (AdventureObject item : this.model.getPlayer().inventory) {

            Button itemButton = new Button();
            itemButton.setAccessibleRole(AccessibleRole.BUTTON);
            itemButton.setAccessibleRoleDescription("Inventory Item.");
            itemButton.setAccessibleText(item.getDescription());
            itemButton.setOnAction(e -> {
                this.model.getPlayer().dropObject(item.getName());
                updateItems();
                objectsInInventory.getChildren().remove(itemButton);
            });

            // Now load the image
            String imagePath = model.getDirectoryName() + "/objectImages/" + item.getName() + ".jpg";
            Image itemImage = new Image(new File(imagePath).toURI().toString());
            ImageView imageView = new ImageView(itemImage);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            // Add the name of the object under its photo
            Label imageLabel = new Label(item.getName());
            imageLabel.setStyle("-fx-text-fill: black;");
            imageLabel.setWrapText(true);
            imageLabel.setPrefSize(100,10);
            VBox imageBox = new VBox();
            imageBox.getChildren().addAll(imageView, imageLabel);
            imageBox.setAlignment(Pos.TOP_CENTER);

            itemButton.setGraphic(imageBox);

            objectsInInventory.getChildren().add(itemButton);

        }

        // Do the same as above but for clues
        for (AdventureClue item : this.model.getPlayer().clueInventory) {

            Button itemButton = new Button();
            itemButton.setAccessibleRole(AccessibleRole.BUTTON);
            itemButton.setAccessibleRoleDescription("Clue Item.");
            itemButton.setAccessibleText(item.getDescription());
            itemButton.setOnAction(e -> {
                this.model.getPlayer().dropClue(item.getName());
                updateItems();
                objectsInInventory.getChildren().remove(itemButton);
            });

            // Now load the image
            String imagePath = model.getDirectoryName() + "/clueImages/" + item.getName() + ".jpg";
            Image itemImage = new Image(new File(imagePath).toURI().toString());
            ImageView imageView = new ImageView(itemImage);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            // Add the name of the object under its photo
            Label imageLabel = new Label(item.getName());
            imageLabel.setStyle("-fx-text-fill: black;");
            imageLabel.setWrapText(true);
            imageLabel.setPrefSize(100,10);
            VBox imageBox = new VBox();
            imageBox.getChildren().addAll(imageView, imageLabel);
            imageBox.setAlignment(Pos.TOP_CENTER);

            itemButton.setGraphic(imageBox);

            objectsInInventory.getChildren().add(itemButton);

        }


        //the path to the image of any is as follows:
        //this.model.getDirectoryName() + "/objectImages/" + objectName + ".jpg";

        ScrollPane scO = new ScrollPane(objectsInRoom);
        scO.setPadding(new Insets(10));
        scO.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        scO.setFitToWidth(true);
        gridPane.add(scO,0,1);

        ScrollPane scI = new ScrollPane(objectsInInventory);
        scI.setFitToWidth(true);
        scI.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        gridPane.add(scI,2,1);
    }

    /*
     * Show the game instructions.
     *
     * If helpToggle is FALSE:
     * -- display the help text in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- use whatever GUI elements to get the job done!
     * -- set the helpToggle to TRUE
     * -- REMOVE whatever nodes are within the cell beforehand!
     *
     * If helpToggle is TRUE:
     * -- redraw the room image in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- set the helpToggle to FALSE
     * -- Again, REMOVE whatever nodes are within the cell beforehand!
     */
    public void showInstructions() {

        if (helpToggle) { // If helpToggle is True
            helpToggle = false;

            // Copied from other methods
            VBox roomPane = new VBox(roomImageView,roomDescLabel);
            roomPane.setPadding(new Insets(10));
            roomPane.setAlignment(Pos.TOP_CENTER);
            roomPane.setStyle("-fx-background-color: #000000;");

            // Since it was true, it means there was a VBOX there
            // Remove whatever was in 1,1
            gridPane.getChildren().remove(instructionBox);
            gridPane.add(roomPane, 1, 1);

        } else { // If false
            helpToggle = true;
            instructionBox = new VBox();
            VBox roomPane = new VBox(roomImageView,roomDescLabel);

            // Displaying the help text by creating a VBOX
            instructionBox.setAlignment(Pos.CENTER);

            // Text area to display the instructions in the VBOX
            TextArea instructionText = new TextArea();
            instructionText.setWrapText(true);

            instructionText.setFont(new Font("Arial", 12));
            instructionText.setPrefSize(400, 350);
            instructionText.setFocusTraversable(true); // makes it scrollable
            instructionText.setText(this.model.getInstructions());

            // Add text into VBOX
            instructionBox.getChildren().add(instructionText);

            // Clear existing content in 1,1 and add the VBOX there
            gridPane.getChildren().remove(roomPane);
            gridPane.add(instructionBox, 1, 1);

        }
    }

    /**
     * This method handles the event related to the
     * help button.
     */
    public void addInstructionEvent() {
        helpButton.setOnAction(e -> {
            stopArticulation(); //if speaking, stop
            showInstructions();
        });
    }

    /**
     * This method handles the event related to the
     * save button.
     */
    public void addSaveEvent() {
        saveButton.setOnAction(e -> {
            gridPane.requestFocus();
            SaveView saveView = new SaveView(this);
        });
    }

    /**
     * This method handles the event related to the
     * load button.
     */
    public void addLoadEvent() {
        loadButton.setOnAction(e -> {
            gridPane.requestFocus();
            LoadView loadView = new LoadView(this);
        });
    }

    public void addViewSuspectsEvent() {
        viewSuspects.setOnAction(e -> {
            gridPane.requestFocus();
            SuspectView suspectView = new SuspectView(this);
        });
    }



    /**
     * This method articulates Room Descriptions
     */
    public void articulateRoomDescription() {
        String desc = this.model.player.getCurrentRoom().getRoomDescription();
        this.voice.speak(desc);

    }

    /**
     * This method stops articulations 
     * (useful when transitioning to a new room or loading a new game)
     */
    public void stopArticulation() {
        if (mediaPlaying) {
            mediaPlayer.stop(); //shush!
            mediaPlaying = false;
        }
    }
}
