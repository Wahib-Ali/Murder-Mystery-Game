import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AdventureModel.AdventureGame;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import views.AdventureGameView;
import views.time;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAdventureTest {

    //test for 1.1
    @Test
    void VisualizingRoom() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        int numOfRooms = game.getRooms().size();
        assertEquals(numOfRooms, 12);
    }

    //test for 1.2
    @Test
    void VisualizingObject() throws IOException {
        //
    }

    //test for 1.3
    @Test
    void RoomConnection() throws IOException {
        AdventureGame adventureGame = new AdventureGame("TinyGame");
        assertEquals("mansion_library", adventureGame.getRooms().get(1).getRoomName());
        assertEquals("mansion_billiard_room", adventureGame.getRooms().get(12).getRoomName());
    }

    //test for 1.4
    @Test
    void Timer() throws IOException {
        time Time = new time(1,0);
        Time.oneSecondPassed();
        assertEquals("0:59", Time.getCurrentTime());
    }

    //test for 2.1
    @Test
    void CluesFeature() throws IOException {
        // Test if the clues are in the correct rooms
        AdventureGame game = new AdventureGame("TinyGame");
        assertEquals(game.getRooms().get(1).cluesInRoom.get(0).getDescription(), "A small strand of soft chestnut coloured hair is on the ground, a couple feet away from where the body was."); // Room 1
        assertEquals(game.getRooms().get(8).cluesInRoom.get(0).getDescription(), "A paper with the title \"DIVORCE\" are on the kitchen counter-top."); // Room 8
        assertEquals(game.getRooms().get(4).cluesInRoom.get(0).getDescription(), "A personal diary with an unknown author is found on the nightstand expressing the frustrations towards her husband."); // Room 4
        assertEquals(game.getRooms().get(5).cluesInRoom.get(0).getDescription(), "A pair of destroyed wedding rings is on the bathroom counter."); // Room 5
    }


    //test for 2.2
    @Test
    void TimerEnds() throws IOException {
        // can only be tested manually
    }

    //test for 2.3
    @Test
    void testEndScreenView() {
        // Initialize EndScreenView
        EndScreenView endScreenView = new EndScreenView(adventureGameView, 1);

        // Assert that the EndScreenView is not null
        assertNotNull(endScreenView);

        // Assert that the gridPane of AdventureGameView is not null
        assertNotNull(adventureGameView.gridPane);

        // Assert that the gridPane of AdventureGameView is cleared
        assertEquals(0, adventureGameView.gridPane.getChildren().size());
    }

    //test for 2.4
    @Test
    void SuspectFeature() throws IOException {
        AdventureGame adventureGame = new AdventureGame("TinyGame");

        assertEquals("Ethan Monroe", adventureGame.getSuspects().get(1).getName());
        assertFalse(adventureGame.getSuspects().get(1).isMurderer());
        assertTrue(adventureGame.getSuspects().get(4).isMurderer());
    }

    //test for 3.1
    @Test
    void ColourInverted() throws IOException {
        // can only be tested manually
    }

    //test for 3.2
    @Test
    void Sound() throws IOException {
        // can only be tested manually
    }

    //test for 3.5
    @Test
    void TimerIssue() throws IOException {
        // can only be tested manually
    }


}
