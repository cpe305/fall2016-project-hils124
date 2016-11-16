package abandoned.entities;

import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Interface to model entity actions.
 * @author hils124
 */

public interface EntityAction {
  
  /**
   * Performs chosen item's use case.
   * @param player - current player
   * @param item - item that is being used
   * @param house - main house
   * @param curRoom - current room the player is in
   * @param curWall - current wall the player is facing
   * @return item use success
   * @throws Exception
   * 
   */
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall)
      throws Exception;
}
