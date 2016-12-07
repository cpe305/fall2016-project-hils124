package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class LetterActionTest extends AbandonedTest {
  
  private static Item letter;
  
  static {
    letter = new Item("letter", "You see letter.", EntityUseType.LETTER,
        true);
    player.addItem(letter);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getSouthWall());
    player.useItem(letter);
    Assert.assertNotNull(player.getItem("letter"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(letter);
    Assert.assertNotNull(player.getItem("letter"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("library");
    Wall curWall = curRoom.getEastWall();
    Container fireplace = curWall.getContainer("fireplace");
    Assert.assertNull(fireplace.getItem("passage"));
    curWall = curRoom.getSouthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(letter);
    Assert.assertNull(player.getItem("letter"));
    Assert.assertNotNull(fireplace.getItem("passage"));
    player.addItem(letter);
  }
}