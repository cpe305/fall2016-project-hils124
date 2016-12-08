package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.Print;
import abandoned.house.Container;
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
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (curWall.getContainer("fire") != null) {
      return false;
    }
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You shine the flashlight down the " + Ansi.PORTAL + "passageway " 
          + Ansi.RESET + "and find it is now lit enough to go through.\n\n", true);
      curWall.setPortal(new Portal("atticDoor", "passageway", "attic", "eastWall", ""));
      Container fireplace = curWall.getContainer("fireplace");
      fireplace.setInspectDescript("You see a " + Ansi.PORTAL + "passageway" + Ansi.RESET 
          + " inside the fireplace.");
      Item passage = fireplace.getItem("passage");
      fireplace.removeItem(passage);
      return true;
    }
    return false;
  }
}