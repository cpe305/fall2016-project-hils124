package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a handle's action when used.
 * 
 * @author hils124
 */
public class ScissorsAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "You use the scissors to snip a string on the wind chime and watch as a "
          + "" + Ansi.ITEM + "key" + Ansi.RESET + " falls to the floor.\n",
          true);
      Container chimes = curWall.getContainer("chimes");
      chimes.setInspectDescript("");
      curWall.addItem(
          new Item("key", "Hidden in the grass below the wind chimes is a " + Ansi.ITEM + "key" 
          + Ansi.RESET + ".", EntityUseType.KEY, true));
      return true;
    }
    return false;
  }
}