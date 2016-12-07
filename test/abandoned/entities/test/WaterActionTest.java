package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class WaterActionTest extends AbandonedTest {
  
  private static Item water;
  
  static {
    water = new Item("water", "", EntityUseType.WATER, true);
    player.addItem(water);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("bedroom");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(water);
    Assert.assertNotNull(player.getItem("water"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(water);
    Assert.assertNotNull(player.getItem("water"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("library");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(water);
    Container fireplace = curWall.getContainer("fireplace");
    Assert.assertNull(fireplace.getItem("fire"));
    player.addItem(water);
  }
}