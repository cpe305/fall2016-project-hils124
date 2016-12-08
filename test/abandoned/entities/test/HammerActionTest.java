package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class HammerActionTest extends AbandonedTest {
  private static Item hammer;
  
  static {
    hammer = new Item("hammer", "You see a hammer.", EntityUseType.HAMMER,
        true);
    player.addItem(hammer);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(hammer);
    Assert.assertNotNull(player.getItem("hammer"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("attic");
    Wall curWall = curRoom.getNorthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(hammer);
    Assert.assertNotNull(player.getItem("hammer"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("attic");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(hammer);
    Assert.assertNull(player.getItem("hammer"));
    Container clock = curWall.getContainer("clock");
    Assert.assertNotNull(clock.getItem("artifact"));
    Assert.assertEquals("", clock.getInspectDescript());
    player.addItem(hammer);
  }
}