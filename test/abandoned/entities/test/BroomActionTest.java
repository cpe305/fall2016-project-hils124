package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;


public class BroomActionTest extends AbandonedTest {
  
  private static Item broom;
  
  static {
    broom = new Item("broom", "You see a broom.", EntityUseType.BROOM,
        true);
  }

  @Test
  public void testIncorrectActionRoom() {
    player.addItem(broom);
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    player.useItem(broom);
    Assert.assertNotNull(player.getItem("broom"));
    player.removeItem(broom);
  }
  
  @Test
  public void testIncorrectActionWall() {
    player.addItem(broom);
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(broom);
    Assert.assertNotNull(player.getItem("broom"));
    player.removeItem(broom);
  }
  
  @Test
  public void testCorrectActionWall() {
    player.addItem(broom);
    Room curRoom = house.getRoom("garden");
    Wall curWall = curRoom.getNorthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(broom);
    Assert.assertNull(player.getItem("broom"));
    Container tree = curWall.getContainer("tree");
    Assert.assertEquals("", tree.getInspectDescript());
    Assert.assertNotNull(curWall.getItem("handle"));
  }
}
