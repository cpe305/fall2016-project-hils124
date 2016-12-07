package abandoned.game.test;

import abandoned.commands.Player;
import abandoned.house.Portal;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest extends AbandonedTest {

  @Test
  public void testCurWall() {
    Wall wall = house.getRoom("bedroom").getEastWall();
    player.setCurrentWall(wall);
    Assert.assertEquals("BEDROOM - EAST", player.getCurrentWall().getName()); 
  }
  
  @Test
  public void testCurRoom() {
    Room room = house.getRoom("kitchen");
    player.setCurrentRoom(room);
    Assert.assertEquals("kitchen", player.getCurrentRoom().getName());
  }
  
  @Test
  public void testInventoryActions() {
//    Assert.assertTrue(player.getInventory().size() == 0);
//    Item item = new Item();
//    player.addItem(item);
//    Assert.assertTrue(player.getInventory().size() == 1);
//    player.removeItem(item);
//    Assert.assertTrue(player.getInventory().size() == 0);
  }
  
  @Test
  public void testTurnRight() {
    Player newPlayer = new Player();
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Assert.assertEquals("BEDROOM - NORTH", newPlayer.getCurrentWall().getName());
    newPlayer.turnRight();
    Assert.assertEquals("BEDROOM - EAST", newPlayer.getCurrentWall().getName());
    newPlayer.turnRight();
    Assert.assertEquals("BEDROOM - SOUTH", newPlayer.getCurrentWall().getName());
  }
  
  @Test
  public void enterPortal() throws Exception {
    Player newPlayer = new Player();
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Room room = house.getRoom("kitchen");
    newPlayer.setCurrentRoom(room);
    Wall wall = room.getWestWall();
    newPlayer.setCurrentWall(wall);
    Assert.assertEquals("kitchen", newPlayer.getCurrentRoom().getName());
    Portal portal = new Portal("bedroomDoor", "door", "bedroom", "westWall",
        "You see a white door");
    newPlayer.enter(portal);
    Assert.assertEquals("BEDROOM - WEST", newPlayer.getCurrentWall().getName());
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());  
  }
  
  @Test
  public void testTurnLeft() {
    Player newPlayer = new Player();
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Assert.assertEquals("BEDROOM - NORTH", newPlayer.getCurrentWall().getName());
    newPlayer.turnLeft();
    Assert.assertEquals("BEDROOM - WEST", newPlayer.getCurrentWall().getName());
    newPlayer.turnLeft();
    Assert.assertEquals("BEDROOM - SOUTH", newPlayer.getCurrentWall().getName());
  }
  
  @Test
  public void testTurnAround() {
    Player newPlayer = new Player();
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Assert.assertEquals("BEDROOM - NORTH", newPlayer.getCurrentWall().getName());
    newPlayer.turnAround();
    Assert.assertEquals("BEDROOM - SOUTH", newPlayer.getCurrentWall().getName());
  }
}