package abandoned.entities;

import abandoned.game.Main;
import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

public class PortalAction {
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) throws Exception {
    curWall.describe();
    return true;
  }
}
