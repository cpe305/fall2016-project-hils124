package abandoned.game.test;

import abandoned.game.GameBuilder;
import abandoned.game.Player;
import abandoned.game.PlayerActions;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Room;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore public class PlayerActionsTest {
  private static House house;
  private static Player player;
  
  static {
    house = GameBuilder.newHouse();
    player = new Player(house);
  }

  @Test
  public void testEnterBadPortal() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    PlayerActions.enterPortal(player, "door", house);
    Assert.assertEquals(player.getCurrentRoom(), curRoom);
    Assert.assertEquals(player.getCurrentWall().getDirection(), "e");
  }
  
  @Test
  public void testEnterPortal1() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    PlayerActions.enterPortal(player, "door", house);
    Assert.assertEquals(player.getCurrentRoom().getName(), "garden");
    Assert.assertEquals(player.getCurrentWall().getDirection(), "n");
  }
  
  @Test
  public void testEnterPortal2() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    PlayerActions.enterPortal(player, "door", house);
    Assert.assertEquals(player.getCurrentRoom().getName(), "bedroom");
    Assert.assertEquals(player.getCurrentWall().getDirection(), "w");
  }
  
  @Test
  public void testInspectBadElement() {
    Room curRoom = house.getRoom("bedroom");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    PlayerActions.inspectElement(player, "desk");
    Container desk = curRoom.getWestWall().getContainer("desk");
    Assert.assertFalse(desk.getInspected());
  }
  
  @Test
  public void testInspectElement1() {
    Room curRoom = house.getRoom("bedroom");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getNorthWall());
    PlayerActions.inspectElement(player, "painting");
    Container painting = player.getCurrentWall().getContainer("painting");
    Assert.assertTrue(painting.getInspected());
  }
  
  @Test
  public void testInspectElement2() {
    Room curRoom = house.getRoom("library");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getWestWall());
    PlayerActions.inspectElement(player, "bookshelf");
    Container desk = player.getCurrentWall().getContainer("bookshelf");
    Assert.assertTrue(desk.getInspected());
  }
}