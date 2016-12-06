package abandoned.entities;

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
          "The handle fits into the \u001B[32mcabinet\u001B[0m and you slowly open it.\n", true);
      Container cabinet = curWall.getContainer("cabinet");
      cabinet.setDescription("You see a dusty \u001B[32mcabinet\u001B[0m with an open drawer.");
      cabinet.addItem(new Item("bucket",
          "There is a \u001B[36mbucket\u001B[0m on the bottom shelf. ",
          EntityUseType.BUCKET, true));
      cabinet.addItem(new Item("knife",
          "You can just make out a \u001B[36mknife\u001B[0m lying on the top shelf.",
          EntityUseType.KNIFE, true));
      return true;
    }
    return false;
  }
}
