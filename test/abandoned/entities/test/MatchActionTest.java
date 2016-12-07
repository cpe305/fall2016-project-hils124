package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class MatchActionTest extends AbandonedTest {
  
  private static Item matches;
  
  static {
    matches = new Item("matches", "You see matches.", EntityUseType.MATCHES,
        true);
    player.addItem(matches);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(matches);
    Assert.assertNotNull(player.getItem("matches"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(matches);
    Assert.assertNotNull(player.getItem("matches"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("library");
    Wall curWall = curRoom.getEastWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(matches);
    curWall = curRoom.getNorthWall();
    Assert.assertNotNull(curWall.getItem("flashlight"));
    player.addItem(matches);
  }
}