package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class KeyActionTest extends AbandonedTest {
  
  private static Item key;
  
  static {
    key = new Item("key", "You see key.", EntityUseType.KEY,
        true);
    player.addItem(key);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getSouthWall());
    player.useItem(key);
    Assert.assertNotNull(player.getItem("key"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    player.useItem(key);
    Assert.assertNotNull(player.getItem("key"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Wall curWall = house.getRoom("bedroom").getSouthWall();
    Assert.assertNull(curWall.getPortal());
    Room curRoom = house.getRoom("attic");
    curWall = curRoom.getSouthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(key);
    Assert.assertNull(player.getItem("key"));
    curWall = house.getRoom("bedroom").getSouthWall();
    Assert.assertNotNull(curWall.getPortal());
    player.addItem(key);
  }
}