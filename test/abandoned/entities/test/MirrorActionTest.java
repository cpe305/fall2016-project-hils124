package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class MirrorActionTest extends AbandonedTest {
  
  private static Item mirror;
  
  static {
    mirror = new Item("mirror", "You see mirror.", EntityUseType.MIRROR,
        true);
    player.addItem(mirror);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(mirror);
    Assert.assertNotNull(player.getItem("mirror"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(mirror);
    Assert.assertNotNull(player.getItem("mirror"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(mirror);
    Assert.assertNull(player.getItem("mirror"));
    Assert.assertNull(curWall.getContainer("snake"));
    Assert.assertNotNull(curWall.getContainer("mound"));
    player.addItem(mirror);
  }
}