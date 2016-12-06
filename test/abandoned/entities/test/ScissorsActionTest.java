package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class ScissorsActionTest extends AbandonedTest {
  
  private static Item scissors;
  
  static {
    scissors = new Item("scissors", "You see scissors.", EntityUseType.SCISSORS,
        true);
    player.addItem(scissors);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(scissors);
    Assert.assertNotNull(player.getItem("scissors"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(scissors);
    Assert.assertNotNull(player.getItem("scissors"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    Wall curWall = curRoom.getSouthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(scissors);
    Assert.assertNull(player.getItem("scissors"));
    Assert.assertNotNull(curWall.getItem("key"));
    Container chimes = curWall.getContainer("chimes");
    Assert.assertEquals("", chimes.getInspectDescript());
    player.addItem(scissors);
  }
}