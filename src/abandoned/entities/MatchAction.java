package abandoned.entities;

import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a match action when used.
 * 
 * @author hils124
 */
public class MatchAction extends EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    Item passage = curWall.getContainer("fireplace").getItem("passage");
    if (passage == null) {
      Print.printString("Lighting the fireplace right now wouldn't be a good idea.\n", true);
      return false;
    }
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "You strike a match and hold it over the fireplace to "
              + "peer into the passageway. All of a sudden, you hear a floorboard creak behind you "
              + "and, startled, you drop the match. The whole fireplace goes up into flames as you "
              + "step back quickly. Oh no, you're never getting into that passageway unless you "
              + "put out that fire!\n", true);
      Container fireplace = curWall.getContainer("fireplace");
      fireplace.setInspectDescript("Through the crackling flames in the fireplace, you can see "
          + "the passageway.");
      fireplace.addItem(new Item("fire", "", null, false));
      Wall northWall = curRoom.getNorthWall();
      northWall.addItem(new Item("flashlight", " You notice a \u001B[36mflashlight\u001B[0m propped"
          + " against the door. A shiver creeps down your spine...has that always been there?", 
          EntityUseType.FLASHLIGHT, true));
      return true;
    }
    return false;
  }
}