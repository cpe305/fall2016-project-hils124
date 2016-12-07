package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class BucketActionTest extends AbandonedTest {
  
  private static Item bucket;
  
  static {
    bucket = new Item("bucket", "You see bucket.", EntityUseType.BUCKET,
        true);
    player.addItem(bucket);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("attic");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    player.useItem(bucket);
    Assert.assertNotNull(player.getItem("bucket"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(bucket);
    Assert.assertNotNull(player.getItem("bucket"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    Wall curWall = curRoom.getWestWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(bucket);
    Assert.assertNull(player.getItem("bucket"));
    Assert.assertNotNull(player.getItem("water"));
    player.addItem(bucket);
  }
}