package abandoned.entities;

import abandoned.game.GlobalHouse;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Portal;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a key's action when used.
 * 
 * @author hils124
 */
public class KeyAction extends EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "The key fits perfectly into the lock and clicks open. You notice a lever inside. "
          + "Curious, you pull it down.\n\n **METALLIC SHIFTING SOUNDS**\n\n ...What could "
          + "that have been? Sounds like it came from either the kitchen or bedroom", true);
      Container box = curWall.getContainer("box");
      box.setInspectDescript("The electric box has a lever that has been pulled down.");
      Room bedroom = GlobalHouse.get().getRoom("bedroom");
      Wall southWall = bedroom.getSouthWall();
      southWall.setPortal(new Portal("basementDoor", "trapdoor", "basement", "southWall", 
          "Your heart races as you see that the wooden floor has shifted to reveal a "
          + "small \u001B[33mtrapdoor\u001B[0m. That must have happened when you pulled the lever "
          + "in the attic!"));
      return true;
    }
    return false;
  }
}
