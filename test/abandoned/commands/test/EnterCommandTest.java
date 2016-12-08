package abandoned.commands.test;

import abandoned.game.Main;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class EnterCommandTest extends AbandonedTest {
  
  @Test
  public void testWrongEntering() {
    Room attic = house.getRoom("attic");
    player.setCurrentRoom(attic);
    Wall curWall = attic.getEastWall();
    player.setCurrentWall(curWall);
    Main.optionParser("enter passageway");
    Assert.assertEquals(attic, player.getCurrentRoom());
    Main.optionParser("turn around");
    Main.optionParser("enter door");
    Assert.assertEquals(attic, player.getCurrentRoom());
  }
  
  @Test
  public void testCorrectEntering() {
    Room attic = house.getRoom("attic");
    player.setCurrentRoom(attic);
    Wall curWall = attic.getWestWall();
    player.setCurrentWall(curWall);
    Main.optionParser("enter passageway");
    Room newRoom = house.getRoom("library");
    Assert.assertEquals(newRoom, player.getCurrentRoom());
  }
}
