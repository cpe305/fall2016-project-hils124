package abandoned.commands.test;

import abandoned.game.Main;
import abandoned.game.test.AbandonedTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class SaveCommandTest extends AbandonedTest {
  
  @Test
  public void testSaving() {
    File houseFile = new File("resources/saveHouse.json");
    File playerFile = new File("resources/savePlayer.json");
    Assert.assertFalse(houseFile.exists());
    Assert.assertFalse(playerFile.exists());
    Main.optionParser("save");
    Assert.assertTrue(houseFile.exists());
    Assert.assertTrue(playerFile.exists());
    houseFile.delete();
    playerFile.delete();
    Assert.assertFalse(houseFile.exists());
    Assert.assertFalse(playerFile.exists());
  }
}