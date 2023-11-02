
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AdventureModel.AdventureGame;
import org.junit.jupiter.api.Test;

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


}
