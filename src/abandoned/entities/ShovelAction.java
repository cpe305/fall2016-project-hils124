package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a shovel's action when used.
 * 
 * @author hils124
 */
public class ShovelAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (curWall.getContainer("snake") != null) {
      return false;
    }
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You use the shovel to scoop away the dirt.\n\n", true); 
      Container mound = curWall.getContainer("mound");
      mound.setInspectDescript("");
      mound.addItem(new Item("artifact",
          "There is a golden, triangular " + Ansi.ITEM + "artifact" + Ansi.RESET + " lying in "
              + "the dirt. ", EntityUseType.ARTIFACT, true)); 
      mound.addItem(new Item("bar", "You scoop away more dirt and find a dirty pry " + Ansi.ITEM 
          + "bar." + Ansi.RESET, EntityUseType.BAR, true)); 
      return true;
    }
    return false;
  }
}