package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a handle's action when used.
 * 
 * @author hils124
 */
public class ScissorsAction implements EntityAction {
  @Override
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "You use the scissors to snip a string on the wind chime and watch as a "
          + "\u001B[36mkey\u001B[0m falls to the floor.\n",
          true);
      Container chimes = curWall.getContainer("chimes");
      chimes.setInspectDescript("");
      curWall.addItem(
          new Item("key", "Hidden in the grass below the wind chimes is a \u001B[36mkey\u001B[0m.",
              EntityUseType.KEY, true));
      return true;
    }
    return false;
  }
}