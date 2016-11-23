package abandoned.entities;

import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a key's action when used.
 * 
 * @author hils124
 */
public class KeyAction implements EntityAction {
  @Override
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    return true;
  }
}
