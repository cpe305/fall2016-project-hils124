package abandoned.house.test;

import abandoned.entities.Item;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RoomTest {
  private static Room room;
  private static Wall newWall;
  private static Wall wall1; 
  
  static {
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Container> containers = new ArrayList<Container>();
    wall1 = new Wall("BEDROOM - WEST", "w", containers, items, null);
    Wall wall2 = new Wall("BEDROOM - EAST", "e", containers, items, null);
    Wall wall3 = new Wall("BEDROOM - NORTH", "n", containers, items, null);
    Wall wall4 = new Wall("BEDROOM - SOUTH", "s", containers, items, null);
    newWall = new Wall("KITCHEN - EAST", "e", containers, items, null);
    room = new Room("bedroom", "", wall1, wall2, wall3, wall4);
  }
  
  @Test
  public void testCreateRoom() {
    Assert.assertEquals(room.getName(), "bedroom");
    Assert.assertEquals(room.getWestWall().getName(), "BEDROOM - WEST");
    Assert.assertEquals(room.getEastWall().getName(), "BEDROOM - EAST");
    Assert.assertEquals(room.getNorthWall().getName(), "BEDROOM - NORTH");
    Assert.assertEquals(room.getSouthWall().getName(), "BEDROOM - SOUTH");
    room.setName("kitchen");
    Assert.assertEquals(room.getName(), "kitchen");
    room.setEastWall(newWall);
    Assert.assertEquals(room.getEastWall(), newWall);
    Assert.assertEquals(room.getEastWall().getName(), "KITCHEN - EAST");
  }
  
  @Test
  public void testEnter() {
    Wall wall = room.enter("westWall"); 
    Assert.assertEquals(wall1, wall);
    Assert.assertEquals("BEDROOM - WEST", wall.getName());
  }
}
