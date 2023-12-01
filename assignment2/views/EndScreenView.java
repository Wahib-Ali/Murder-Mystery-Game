package views;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class EndScreenView {
    boolean win = false;
    public String suspectList;
    public String endingtext;
    public EndScreenView(AdventureGameView view){
        view.gridPane.getChildren().clear();

        Label who = new Label("\nPick a suspect. Now!!");
        Label suspect = new Label("Suspect 1: Ethan Monroe, 19\nSuspect 2: Maria Sanchez, 42, Maid\nSuspect 3: Alfred Thompson, 50, Butler\n" +
                "Suspect 4:Victoria Sinclair");
        who.setAlignment(Pos.CENTER);
        who.setStyle("-fx-text-fill: white;");
        who.setFont(new Font("Arial", 30));

        suspect.setAlignment(Pos.CENTER);
        suspect.setStyle("-fx-text-fill: white;");
        suspect.setFont(new Font("Arial", 20));

        Label ending = new Label ("\n" +
                "Victoria Sinclair's discontent within her marriage reached a boiling point, and her desire for financial stability \n" +
                "and independence drove her to contemplate extreme measures. The strained relationship with her husband, the victim, \n" +
                " had led her to discover an unusual key among his possessions—one she believed could unlock the door to a new life.\n" +
                "\n" +
                "Maria Sanchez, the long-serving maid, observed the growing tension within the household but remained dedicated \n" +
                "to her duties. Unbeknownst to Victoria, Maria harbored no desire for involvement in the family's internal struggles.\n" +
                " She had always maintained a facade of contentment, and her loyalty to the victim's family was genuine.\n" +
                "\n" +
                "One evening, as the victim retreated to his study, Victoria saw an opportunity to carry out her plan.\n" +
                " Armed with a cold, discarded gun, she entered the room alone, leaving Maria oblivious to the impending tragedy. \n" +
                "The shards of broken glass and a faint footprint bore witness to the intrusion and the ensuing struggle.\n" +
                "\n" +
                "Victoria confronted her husband, wielding the gun as a tool of her deadly act. The silent storyteller, \n" +
                "a blood-stained knife, became evidence of the violent confrontation that unfolded within the study. \n" +
                "As the victim fell, lifeless, to the floor, Victoria, driven by desperation, made her escape.\n" +
                "\n" +
                "Maria, continuing her duties elsewhere in the mansion, remained oblivious to the unfolding tragedy. \n" +
                "The enigmatic black hair found at the crime scene linked directly to Victoria,\n" +
                " who had left behind a shattered family and a facade of marital bliss.\n" +
                "\n" +
                "In the aftermath, investigators discovered the unusual key and the journal with the haunting sentence,\n \"" +
                "Why am I dying to live if I'm just living to die?\" These artifacts became keys to unraveling the mystery\n" +
                " behind the husband's murder, revealing Victoria Sinclair's desperate quest for a new life, \n" +
                "free from the constraints of her unhappy marriage. Maria, innocent and loyal, found herself unwittingly\n" +
                " entangled in a web of secrets that had long festered within the walls of the mansion.");

        ending.setAlignment(Pos.CENTER);
        ending.setStyle("-fx-text-fill: white;");
        ending.setFont(new Font("Arial", 12));

        Button s1 = new Button("Suspect 1");
        s1.setId("s1");
        customizeButton(s1, 100, 50);
        s1.setOnAction(e -> {
            view.gridPane.getChildren().clear();

            Label lose = new Label("You Lost!");

            lose.setAlignment(Pos.CENTER);
            lose.setStyle("-fx-text-fill: white;");
            lose.setFont(new Font("Arial", 30));

            view.gridPane.add(lose, 0, 1);

            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
            view.gridPane.add(ending,1,1);
        });

        Button s2 = new Button("Suspect 2");
        s2.setId("s2");
        customizeButton(s2, 100, 50);
        s2.setOnAction(e -> {
            view.gridPane.getChildren().clear();

            Label lose = new Label("You Lost!");

            lose.setAlignment(Pos.CENTER);
            lose.setStyle("-fx-text-fill: white;");
            lose.setFont(new Font("Arial", 30));

            view.gridPane.add(lose, 0, 1);

            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
            view.gridPane.add(ending,1,1);
        });

        Button s3 = new Button("Suspect 3");
        s3.setId("s3");
        customizeButton(s3, 100, 50);
        s3.setOnAction(e -> {
            view.gridPane.getChildren().clear();

            Label lose = new Label("You Lost!");

            lose.setAlignment(Pos.CENTER);
            lose.setStyle("-fx-text-fill: white;");
            lose.setFont(new Font("Arial", 30));

            view.gridPane.add(lose, 0, 1);
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
            view.gridPane.add(ending,1,1);
        });

        Button s4 = new Button("Suspect 4");
        s4.setId("s4");
        customizeButton(s4, 100, 50);
        s4.setOnAction(e -> {
            view.gridPane.getChildren().clear();

            Label win = new Label("You Win!");

            win.setAlignment(Pos.CENTER);
            win.setStyle("-fx-text-fill: white;");
            win.setFont(new Font("Arial", 30));

            view.gridPane.add(win, 0, 1);
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
            view.gridPane.add(ending,1,1);
        });

        HBox options = new HBox();
        options.getChildren().addAll(s1, s2, s3, s4);
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER);

        view.gridPane.add( options, 0, 2, 3, 1 );
        view.gridPane.add(who, 1, 0);
        view.gridPane.add(suspect,1,1);





    }

    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", 16));
        inputButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
    }
}
