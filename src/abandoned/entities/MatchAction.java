package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a match's action when used.
 * 
 * @author hils124
 */
public class MatchAction implements EntityAction {
  @Override
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    player.setCanSee(true);
    Print.printString("\nYou strike a match and squint as a room slowly lights up around you.\n",
        true);
    curWall.describe();
    return true;
  }
}
