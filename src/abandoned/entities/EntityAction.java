package abandoned.entities;

import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Interface to model entity actions.
 * @author hils124
 */

public interface EntityAction {
  
  /**
   * Performs chosen item's use case.
   * @param item - item that is being used
   * @param curRoom - current room the player is in
   * @param curWall - current wall the player is facing
   * @return item use success
   * 
   */
  public boolean use(Item item, Room curRoom, Wall curWall);
}