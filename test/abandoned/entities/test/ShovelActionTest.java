package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class ShovelActionTest extends AbandonedTest {
  
  private static Item shovel;
  
  static {
    shovel = new Item("shovel", "You see a shovel.", EntityUseType.SHOVEL,
        true);
  }

  @Test
  public void testIncorrectActionRoom() {
    player.addItem(shovel); 
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(shovel);
    Assert.assertNotNull(player.getItem("shovel"));
    player.removeItem(shovel);
  }
  
  @Test
  public void testIncorrectActionWall() {
    player.addItem(shovel);
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(shovel);
    Assert.assertNotNull(player.getItem("shovel"));
    player.removeItem(shovel);
  }
  
  @Test
  public void testCorrectActionWall() {
    player.addItem(shovel);
    Room curRoom = house.getRoom("garden");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(shovel);
    Assert.assertNull(player.getItem("shovel"));
    Container mound = curWall.getContainer("mound");
    Assert.assertEquals("", mound.getInspectDescript());
    Assert.assertNotNull(mound.getItem("artifact"));
  }
  
}