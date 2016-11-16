package abandoned.house;

import java.util.ArrayList;

/**
 * Class to model a house.
 * @author hils124
 */
public class House {
  private ArrayList<Room> rooms;

  public House() {
  }

  /**
   * Public constructor.
   * @param rooms - list of rooms in the house
   */
  public House(ArrayList<Room> rooms) {
    this.rooms = rooms;
  }

  public ArrayList<Room> getRooms() {
    return this.rooms;
  }
  
  /**
   * Gets the specified room in the house
   * @param name - name of the requested room
   * @return requested room if it is in the house. Else returns null.
   */
  public Room getRoom(String name) {
    for (Room r: this.rooms) {
      if (r.getName().equals(name)) {
        return r;
      }
    }
    return null;
  }
}