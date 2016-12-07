package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class NoteActionTest extends AbandonedTest {
  
  private static Item note;
  
  static {
    note = new Item("note", "You see note.", EntityUseType.NOTE,
        true);
    player.addItem(note);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    player.useItem(note);
    Assert.assertNotNull(player.getItem("note"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getSouthWall());
    player.useItem(note);
    Assert.assertNotNull(player.getItem("note"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("attic");
    Wall curWall = curRoom.getNorthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(note);
    Assert.assertNull(player.getItem("note"));
    Container trunk = curWall.getContainer("trunk");
    Assert.assertEquals("", trunk.getInspectDescript());
    Assert.assertNotNull(trunk.getItem("mirror"));
    Assert.assertNotNull(trunk.getItem("artifact"));
    player.addItem(note);
  }
}