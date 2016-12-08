package abandoned.commands.test;

import abandoned.entities.Item;
import abandoned.game.Main;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class InspectCommandTest extends AbandonedTest {
  private static Container bookshelf;
  private static Container bed;
  
  static {
    Room library = house.getRoom("library");
    player.setCurrentRoom(library);
    Wall curWall = library.getWestWall();
    player.setCurrentWall(curWall);
    bookshelf = curWall.getContainer("bookshelf");
    Room bedroom = house.getRoom("bedroom");
    player.setCurrentRoom(bedroom);
    curWall = bedroom.getSouthWall();
    player.setCurrentWall(curWall);
    bed = curWall.getContainer("bed");
  }
  
  @Test
  public void testInspectBookshelf() {
    Room library = house.getRoom("library");
    player.setCurrentRoom(library);
    Wall curWall = library.getEastWall();
    player.setCurrentWall(curWall);
    Main.optionParser("inspect bookshelf");
    Assert.assertFalse(bookshelf.getInspected());
    player.setCurrentWall(library.getWestWall());
    Main.optionParser("take pin");
    Assert.assertNull(player.getItem("pin"));
    Main.optionParser("inspect bookshelf");
    Assert.assertTrue(bookshelf.getInspected());
    Main.optionParser("take pin");
    Assert.assertNotNull(player.getItem("pin"));
    Item pin = player.getItem("pin");
    player.removeItem(pin);
  }
  
  @Test
  public void testInspectBed() {
    Room bedroom = house.getRoom("bedroom");
    player.setCurrentRoom(bedroom);
    Wall curWall = bedroom.getEastWall();
    player.setCurrentWall(curWall);
    Main.optionParser("inspect bed");
    Assert.assertFalse(bed.getInspected());
    player.setCurrentWall(bedroom.getSouthWall());
    Main.optionParser("take shovel");
    Assert.assertNull(player.getItem("shovel"));
    Main.optionParser("inspect bed");
    Assert.assertTrue(bed.getInspected());
    Main.optionParser("take shovel");
    Assert.assertNotNull(player.getItem("shovel"));
    Item shovel = player.getItem("shovel");
    player.removeItem(shovel);
  }

}