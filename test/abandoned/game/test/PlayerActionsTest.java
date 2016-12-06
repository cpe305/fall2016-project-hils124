package abandoned.game.test;

import abandoned.game.PlayerActions;
import abandoned.house.Container;
import abandoned.house.Room;

import org.junit.Assert;
import org.junit.Test;

public class PlayerActionsTest extends AbandonedTest {

  @Test
  public void testEnterBadPortal() throws Exception {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    PlayerActions.enterPortal("door");
    Assert.assertEquals(player.getCurrentRoom(), curRoom);
    Assert.assertEquals(player.getCurrentWall().getDirection(), "e");
  }
  
  @Test
  public void testEnterPortal1() throws Exception {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    PlayerActions.enterPortal("door");
    Assert.assertEquals(player.getCurrentRoom().getName(), "garden");
    Assert.assertEquals(player.getCurrentWall().getDirection(), "n");
  }
  
  @Test
  public void testEnterPortal2() throws Exception {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    PlayerActions.enterPortal("door");
    Assert.assertEquals(player.getCurrentRoom().getName(), "bedroom");
    Assert.assertEquals(player.getCurrentWall().getDirection(), "w");
  }
  
  @Test
  public void testInspectBadElement() throws Exception {
    Room curRoom = house.getRoom("bedroom");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    PlayerActions.inspectElement("desk");
    Container desk = curRoom.getWestWall().getContainer("desk");
    Assert.assertFalse(desk.getInspected());
  }
  
  @Test
  public void testInspectElement1() throws Exception {
    Room curRoom = house.getRoom("bedroom");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    PlayerActions.inspectElement("painting");
    Container painting = player.getCurrentWall().getContainer("painting");
    Assert.assertTrue(painting.getInspected());
  }
  
  @Test
  public void testInspectElement2() throws Exception {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    PlayerActions.inspectElement("bookshelf");
    Container desk = player.getCurrentWall().getContainer("bookshelf");
    Assert.assertTrue(desk.getInspected());
  }
}