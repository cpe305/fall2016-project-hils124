package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class BarActionTest extends AbandonedTest {
  private static Item bar;
  
  static {
    bar = new Item("bar", "You see a bar.", EntityUseType.BAR,
        true);
    player.addItem(bar);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getSouthWall());
    player.useItem(bar);
    Assert.assertNotNull(player.getItem("bar"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("basement");
    Wall curWall = curRoom.getNorthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(bar);
    Assert.assertNotNull(player.getItem("bar"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("basement");
    Wall curWall = curRoom.getSouthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(bar);
    Assert.assertNull(player.getItem("bar"));
    Wall westWall = house.getRoom("garden").getWestWall();
    Container pond = westWall.getContainer("pond");
    Assert.assertNotNull(pond.getItem("artifact"));
    player.addItem(bar);
  }
}