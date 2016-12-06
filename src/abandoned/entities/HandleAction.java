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
public class HandleAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "The handle fits into the " + Ansi.BIN + "cabinet" 
          + Ansi.RESET + " and you slowly open it.\n", true);
      Container cabinet = curWall.getContainer("cabinet");
      cabinet.setDescription("You see a dusty " + Ansi.BIN + "cabinet " 
          + Ansi.RESET + " with an open drawer.");
      cabinet.addItem(new Item("bucket",
          "There is a " + Ansi.ITEM + "bucket" 
          + Ansi.RESET + " on the bottom shelf. ",
          EntityUseType.BUCKET, true));
      cabinet.addItem(new Item("knife",
          "You can just make out a " + Ansi.ITEM + "knife" 
          + Ansi.RESET + " lying on the top shelf.",
          EntityUseType.KNIFE, true));
      return true;
    }
    return false;
  }
}
