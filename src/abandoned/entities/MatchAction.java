package abandoned.entities;

import abandoned.game.Main;
import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a match's action when used.
 * @author hils124
 */
public class MatchAction implements EntityAction {
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall)
      throws Exception {
    player.setCanSee(true);
    Main.scrollText("\nYou strike a match and squint as a room slowly lights up around you.\n");
    curWall.describe();
    return true;
  }
}
