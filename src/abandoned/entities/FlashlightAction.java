package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a flashlight's action when used.
 * 
 * @author hils124
 */
public class FlashlightAction implements EntityAction {
  @Override
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You shine the flashlight down the \u001B[33mpassageway\u001B[0m "
          + "and find it is now lit enough to go through.\n\n", true);
      curWall.setPortal(new Portal("atticDoor", "passageway", "attic", "eastWall", "You "
          + "slowly creep through the passageway and find a rotting staircase. After "
          + " climbing the creaking stairs you enter what must be the attic."));
      Container fireplace = curWall.getContainer("fireplace");
      fireplace.setInspectDescript("You see a \u001B[33mpassageway\u001B[0m inside the fireplace.");
      Item passage = fireplace.getItem("passage");
      fireplace.removeItem(passage);
      return true;
    }
    return false;
  }
}