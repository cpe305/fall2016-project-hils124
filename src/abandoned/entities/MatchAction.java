package abandoned.entities;

import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

public class MatchAction implements EntityAction {
  public boolean use(Item item, House house, Room curRoom, Wall curWall) {
    System.out.println("Match action");
    return true;
  }
}
