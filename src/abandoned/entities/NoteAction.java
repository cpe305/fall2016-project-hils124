package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a note's action when used.
 * 
 * @author hils124
 */
public class NoteAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "You reach high into the branches with the broom and gently knock the "
          + "object down. The object appears to be a spare " + Ansi.ITEM + "handle" 
          + Ansi.RESET + " to something.\n", true);
      Container trunk = curWall.getContainer("trunk");
      trunk.setInspectDescript("");
      trunk.addItem(new Item("mirror",
          "A compact " + Ansi.ITEM + "mirror" + Ansi.RESET + " blinds you as it "
              + "catches the light. ", EntityUseType.MIRROR, true));
      trunk.addItem(new Item("artifact",
          "You pause as you notice a golden metal " + Ansi.ITEM + "artifact" 
          + Ansi.RESET + " in the shape of a triangle. Could this be the same artifact "
              + "that the letter mentioned?", EntityUseType.ARTIFACT, true));
      return true;
    }
    return false;
  }
}