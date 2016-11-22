package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
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
    Print.printString("\nYou strike a match and squint as a room slowly lights up around you.\n\n",
        true);
    curWall.describe();
    return true;
  }
}
