package abandoned.game.test;

import abandoned.game.GlobalHouse;
import abandoned.game.GlobalPlayer;
import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.House;


/**
 * Abstract class to model entity actions.
 * @author hils124
 */

public abstract class AbandonedTest {
  protected static House house = GlobalHouse.get();
  protected static Player player = GlobalPlayer.get();
  
  /**
   * Public constructor to set house and player instance variables.
   */
  public AbandonedTest() {
    Print.setPrintToConsole(false);
  }

}