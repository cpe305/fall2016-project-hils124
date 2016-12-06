package abandoned.entities;

import abandoned.game.GlobalPlayer;
import abandoned.game.Print;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a artifact's action when used.
 * 
 * @author hils124
 */
public class ArtifactAction extends EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      if (GlobalPlayer.get().getArtifactCount() < 4) {
        Print.printString("You need more artifacts", false);
        return false;
      }
      Print.printString("", true);
      return true;
    }
    return false;
  }
}