package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;

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
}