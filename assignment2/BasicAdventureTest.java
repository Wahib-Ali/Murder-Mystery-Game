
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
    @Test
    void getCommandsTest() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String commands = game.player.getCurrentRoom().getCommands();
        String[] lst = commands.split(",");
        String[] lst2 = {"DOWN", "NORTH", "IN", "WEST", "UP", "SOUTH"};
        if (lst.length == lst2.length) {
            for (String element : lst2) {
                assertEquals(helper(lst, element), true);
            }
        } else{
            // Returns false
            assertEquals(1, 2);
        }
    }
    private static boolean helper(String[] lst, String word) {
        for (String element : lst) {
            if (element.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    @Test
    void getObjectString() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String objects = game.player.getCurrentRoom().getObjectString();
        assertEquals("a water bird", objects);
    }

    //test for 1.1
    @Test
    void VisualizingRoom() throws IOException {
        //
    }

    //test for 1.2
    @Test
    void VisualizingObject() throws IOException {
        //
    }

    //test for 1.3
    @Test
    void RoomConnection() throws IOException {
        //
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
        //
    }

    //test for 2.2
    @Test
    void TimerEnds() throws IOException {
        // can only be tested manually
    }

    //test for 2.3
    @Test
    void End_Of_Game() throws IOException {
        //
    }

    //test for 2.4
    @Test
    void SuspectFeature() throws IOException {
        //
    }

    //test for 3.1
    @Test
    void ColourInverted() throws IOException {
        //
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

    //test for 3.6
    @Test
    void SuspectFeatureFix() throws IOException {
        //
    }


}
