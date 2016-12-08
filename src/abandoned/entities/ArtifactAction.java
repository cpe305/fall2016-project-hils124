package abandoned.entities;

import abandoned.game.GlobalPlayer;
import abandoned.game.Main;
import abandoned.game.Print;
import abandoned.house.Room;
import abandoned.house.Wall;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to model a artifact's action when used.
 * 
 * @author hils124
 */
public class ArtifactAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      if (GlobalPlayer.get().getArtifactCount() == 1) {
        Print.printString("The artifact fits into one of the triangle indentations. "
            + "There must be three more of them lying around somewhere.", false);
        return false;
      } else if (GlobalPlayer.get().getArtifactCount() < 4) {
        Print.printString("The artifacts fit into the triangle indentations. "
            + "There must be more of them lying around somewhere.", false);
        return false;
      } else {
        Print.printString("You slowly fit all four artifacts into their indentations "
            + "on the wall. All of a sudden, the wall starts to rumble and a part of "
            + "it begins to slide away, revealing a small doorway.\n\n", true);
        Print.printString("You cautiously walk through it and notice a trapdoor. You "
            + "push up on the trapdoor and feel sunlight warm your face. You breathe "
            + "in fresh air as you pull yourself out of the room and onto fresh grass.\n\n", true);
        Print.printString("You find you are in the woods that you saw through the fence "
            + "in the garden. You quickly look behind you, expecting to see the house, but "
            + "there is nothing there but more trees...Hurriedly, glad to be rid of the "
            + "place, you run north for several hundred feet until you almost fall as you "
            + "splash right into water.\n\n", true);
        Print.printString("You gulp as you see that your problems haven't ended but have "
            + "only just begun. Stretched ahead of you for miles is an ocean.\n\n You are"
            + "stranded on an island.\n\n", true);
        Print.printString("END OF PART ONE\n", true);
        try {
          Main.endGame();
        } catch (IOException ex) {
          Logger logger = Logger.getLogger("End Game");
          logger.log(Level.SEVERE, "IO Exception", ex);
        }
        return true;
      }
    }
    return false;
  }
}