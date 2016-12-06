package abandoned.entities;

import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a water action when used.
 * 
 * @author hils124
 */
public class WaterAction extends EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    Item fire = curWall.getContainer("fireplace").getItem("fire");
    if (item.getUseType().getWall().equals(curWall.getName()) && fire != null) {
      Print.printString("You dump the water over the fire and smoke flies in your face "
          + "as the flames die down. Unfortunately, the passageway in the fireplace is still too "
          + "dark to enter.\n\n", true);
      Container fireplace = curWall.getContainer("fireplace");
      fireplace.setInspectDescript("The passageway inside the fireplace is still too dark "
          + "to enter.");
      fireplace.removeItem(fire);
      return true;
    }
    return false;
  }
}