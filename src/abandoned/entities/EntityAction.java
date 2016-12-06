package abandoned.entities;

import abandoned.game.GlobalHouse;
import abandoned.game.GlobalPlayer;
import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Abstract class to model entity actions.
 * @author hils124
 */

public abstract class EntityAction {
//  protected static House house;
//  protected static Player player;
//  
//  /**
//   * Public constructor to set house and player instance variables.
//   */
//  public EntityAction() {
//    try {
//      house = GlobalHouse.get();
//      player = GlobalPlayer.get();
//    } catch (Exception ex) {
//      Logger logger = Logger.getLogger("GameState");
//      logger.log(Level.SEVERE, "Problem getting the game.", ex); 
//    }
//  }
  
  
  
  /**
   * Performs chosen item's use case.
   * @param item - item that is being used
   * @param curRoom - current room the player is in
   * @param curWall - current wall the player is facing
   * @return item use success
   * 
   */
  public boolean use(Item item, Room curRoom, Wall curWall) {
    return true;
  }
}
