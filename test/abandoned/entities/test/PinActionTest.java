package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class PinActionTest extends AbandonedTest {
  private static Item pin;
  
  static {
    pin = new Item("pin", "You see a pin.", EntityUseType.PIN,
        true);
    player.addItem(pin);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(pin);
    Assert.assertNotNull(player.getItem("pin"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("bedroom");
    Wall curWall = curRoom.getNorthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(pin);
    Assert.assertNotNull(player.getItem("pin"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("bedroom");
    Wall curWall = curRoom.getWestWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(pin);
    Assert.assertNull(player.getItem("pin"));
    Container desk = curWall.getContainer("desk");
    Assert.assertNotNull(desk.getItem("scissors"));
    Assert.assertEquals("", desk.getInspectDescript());
    player.addItem(pin);
  }
}