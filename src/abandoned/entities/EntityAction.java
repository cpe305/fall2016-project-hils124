package abandoned.entities;

import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;
import abandoned.game.Player;

public interface EntityAction {
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) throws Exception;
}
