package abandoned.house.test;

import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HouseTest {
  private static House house;
  
  static {
    List<Room> rooms = new ArrayList<Room>();
    house = new House(rooms);
  }
  
  @Test
  public void testCreateHouse() {
    Assert.assertTrue(house.getRooms().size() == 0);
    Room room1 = new Room();
    house.addRoom(room1);
    Assert.assertTrue(house.getRooms().size() == 1);
    Room room2 = new Room();
    house.addRoom(room2);
    Assert.assertTrue(house.getRooms().size() == 2);
    house.removeRoom(room1);
    house.removeRoom(room2);
    Assert.assertTrue(house.getRooms().size() == 0);  
  }
  
  @Test
  public void testGetRoom() {
    Wall wall = new Wall();
    Room room1 = new Room("bedroom", "", wall, wall, wall, wall);
    Room room2 = new Room("kitchen", "", wall, wall, wall, wall);
    house.addRoom(room1);
    house.addRoom(room2);
    Assert.assertEquals(house.getRoom("kitchen"), room2);
    house.removeRoom(room2);
    Assert.assertNull(house.getRoom("kitchen"));
  }

}

