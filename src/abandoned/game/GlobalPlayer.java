package abandoned.game;

import abandoned.commands.Player;

/**
 * Class to instantiate one instance of a player (singleton).
 * @author hils124
 */
public class GlobalPlayer {
  private static Player player;
  
  private GlobalPlayer() {
    
  }
  
  /**
   * Method to get single instance of a player.
   * @return Player - instance of a player
   */
  public static Player get() {
    if (player == null) {
      player = new Player();
    }
    return player;  
  }
  
  /**
   * Method to initialize single instance of a player.
   * @param isNew - true if the player needs to be new
   */
  public static void initializePlayer(boolean isNew) {
    if (player != null) {
      return;
    }
    if (isNew) {
      player = new Player();
    } else {
      player = GameLoader.loadPlayer();
    }
  }
}