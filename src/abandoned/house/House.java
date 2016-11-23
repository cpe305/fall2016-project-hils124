package abandoned.house;

import java.util.List;

/**
 * Class to model a house.
 * @author hils124
 */
public class House {
  private List<Room> rooms;

  public House() {
    //empty constructor for JSON serializing
  }

  /**
   * Public constructor.
   * @param rooms - list of rooms in the house
   */
  public House(List<Room> rooms) {
    this.rooms = rooms;
  }

  public List<Room> getRooms() {
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