package abandoned.game.test;

import abandoned.entities.Item;
import abandoned.game.GameBuilder;
import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore public class PlayerTest {
  private static House house;
  private static Player player;
  
  static {
    house = GameBuilder.newHouse();
    player = new Player(house);
  }

  @Test
  public void testCurWall() {
    Assert.assertEquals("BEDROOM - NORTH", player.getCurrentWall().getName());
    Wall wall = house.getRoom("bedroom").getEastWall();
    player.setCurrentWall(wall);
    Assert.assertEquals("BEDROOM - EAST", player.getCurrentWall().getName()); 
  }
  
  @Test
  public void testCurRoom() {
    Assert.assertEquals("bedroom", player.getCurrentRoom().getName());
    Room room = house.getRoom("kitchen");
    player.setCurrentRoom(room);
    Assert.assertEquals("kitchen", player.getCurrentRoom().getName());
  }
  
  @Test
  public void testInventoryActions() {
    Assert.assertTrue(player.getInventory().size() == 0);
    Item item = new Item();
    player.addItem(item);
    Assert.assertTrue(player.getInventory().size() == 1);
    player.removeItem(item);
    Assert.assertTrue(player.getInventory().size() == 0);
  }
  
  @Test
  public void testTurnRight() {
    Player newPlayer = new Player(house);
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Assert.assertEquals("BEDROOM - NORTH", newPlayer.getCurrentWall().getName());
    newPlayer.turnRight();
    Assert.assertEquals("BEDROOM - EAST", newPlayer.getCurrentWall().getName());
    newPlayer.turnRight();
    Assert.assertEquals("BEDROOM - SOUTH", newPlayer.getCurrentWall().getName());
  }
  
  @Test
  public void enterPortal() {
    Player newPlayer = new Player(house);
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Room room = house.getRoom("kitchen");
    newPlayer.setCurrentRoom(room);
    Wall wall = room.getWestWall();
    newPlayer.setCurrentWall(wall);
    Assert.assertEquals("kitchen", newPlayer.getCurrentRoom().getName());
    Portal portal = new Portal("bedroomDoor", "door", "bedroom", "westWall",
        "You see a white door");
    newPlayer.enter(portal, house);
    Assert.assertEquals("BEDROOM - WEST", newPlayer.getCurrentWall().getName());
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());  
  }
  
  @Test
  public void testTurnLeft() {
    Player newPlayer = new Player(house);
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Assert.assertEquals("BEDROOM - NORTH", newPlayer.getCurrentWall().getName());
    newPlayer.turnLeft();
    Assert.assertEquals("BEDROOM - WEST", newPlayer.getCurrentWall().getName());
    newPlayer.turnLeft();
    Assert.assertEquals("BEDROOM - SOUTH", newPlayer.getCurrentWall().getName());
  }
  
  @Test
  public void testTurnAround() {
    Player newPlayer = new Player(house);
    Assert.assertEquals("bedroom", newPlayer.getCurrentRoom().getName());
    Assert.assertEquals("BEDROOM - NORTH", newPlayer.getCurrentWall().getName());
    newPlayer.turnAround();
    Assert.assertEquals("BEDROOM - SOUTH", newPlayer.getCurrentWall().getName());
  }
}