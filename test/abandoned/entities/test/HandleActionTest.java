package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;


public class HandleActionTest extends AbandonedTest {
  
  private static Item handle;
  
  static {
    handle = new Item("handle", "You see a handle.", EntityUseType.HANDLE,
        true);
    player.addItem(handle);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(handle);
    Assert.assertNotNull(player.getItem("handle"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(handle);
    Assert.assertNotNull(player.getItem("handle"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("kitchen");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(handle);
    Assert.assertNull(player.getItem("handle"));
    Container cabinet = curWall.getContainer("cabinet");
    Assert.assertNotNull(cabinet.getItem("knife"));
    player.addItem(handle);
  }
}
