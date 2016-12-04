package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

import java.util.ArrayList;

/**
 * Class to model a bucket's action when used.
 * 
 * @author hils124
 */
public class BucketAction implements EntityAction {
  @Override
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You reach high into the branches with the broom and gently knock the "
          + "object down. The object appears to be a spare \u001B[36mhandle\u001B[0m "
          + "to something.\n", true);
      player.addItem(new Item("water", "", EntityUseType.WATER, true));
      return true;
    }
    return false;
  }
}