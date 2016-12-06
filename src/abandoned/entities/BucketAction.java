package abandoned.entities;

import abandoned.game.GlobalPlayer;
import abandoned.game.Print;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a bucket's action when used.
 * 
 * @author hils124
 */
public class BucketAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You bend down over the pond and gently scoop water into the "
          + "bucket.\n\n", true);
      GlobalPlayer.get().addItem(new Item("water", "", EntityUseType.WATER, true));
      return true;
    }
    return false;
  }
}