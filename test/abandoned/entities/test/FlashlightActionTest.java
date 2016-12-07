package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;


public class FlashlightActionTest extends AbandonedTest {
  
  private static Item flashlight;
  
  static {
    flashlight = new Item("flashlight", "You see a flashlight.", EntityUseType.FLASHLIGHT,
        true);
    player.addItem(flashlight);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(flashlight);
    Assert.assertNotNull(player.getItem("flashlight"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(flashlight);
    Assert.assertNotNull(player.getItem("flashlight"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("library");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(flashlight);
    Assert.assertNull(player.getItem("flashlight"));
    Assert.assertNotNull(curWall.getPortal());
    player.addItem(flashlight);
  }
}