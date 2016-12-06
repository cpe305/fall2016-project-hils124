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
  private static Item matches;
  private static Item letter;
  
  static {
    letter = new Item("letter", "", EntityUseType.LETTER, true);
    matches = new Item("matches", "", EntityUseType.MATCHES, true);
    water = new Item("water", "", EntityUseType.WATER, true);
    player.addItem(letter);
    player.addItem(matches);
    Room curRoom = house.getRoom("library");
    Wall southWall = curRoom.getSouthWall();
    Wall eastWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(southWall);
    player.useItem(letter);
    player.setCurrentWall(eastWall);
    player.useItem(matches);
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
    Assert.assertNull(player.getItem("water"));
    Container fireplace = curWall.getContainer("fireplace");
    Assert.assertNotNull(fireplace.getItem("passage"));
    Assert.assertNull(fireplace.getItem("fire"));
    player.addItem(water);
  }
}