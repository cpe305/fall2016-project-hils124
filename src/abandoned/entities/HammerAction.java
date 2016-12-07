package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a hammer's action when used.
 * 
 * @author hils124
 */
public class HammerAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You hit the hammer on the glass clockface and it shatters.\n\n", true);
      Container clock = curWall.getContainer("clock");
      clock.setInspectDescript("");
      clock.addItem(new Item("artifact", "Past the bits of shard glass, you see a golden "
          + "triangular " + Ansi.ITEM + "artifact" + Ansi.RESET + " acting as the hour hand.", 
          EntityUseType.ARTIFACT, true));
      return true;
    }
    return false;
  }
}