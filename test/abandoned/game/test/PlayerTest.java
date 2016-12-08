package abandoned.game.test;

import abandoned.commands.Player;
import abandoned.entities.Item;
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
  public void testArtifactCount() {
    player.setArtifactCount(1);
    Assert.assertEquals(1, player.getArtifactCount());
    player.incrementArtifactCount();
    player.incrementArtifactCount();
    Assert.assertEquals(3, player.getArtifactCount());
    player.setArtifactCount(0);
    Assert.assertEquals(0, player.getArtifactCount());
    
    
  }
  
  @Test
  public void testInventoryActions() {
    int size = player.getInventory().size();
    Item item = new Item();
    player.addItem(item);
    Assert.assertEquals(size + 1, player.getInventory().size());
    player.removeItem(item);
    Assert.assertEquals(size, player.getInventory().size());
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
    newPlayer.turnRight();
    Assert.assertEquals("BEDROOM - WEST", newPlayer.getCurrentWall().getName());
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
    newPlayer.turnLeft();
    Assert.assertEquals("BEDROOM - EAST", newPlayer.getCurrentWall().getName());
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