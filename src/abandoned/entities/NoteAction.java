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
          "You use the numbers on the note to enter the combination '4, 11, 24' " 
          + Ansi.ITEM + "and" + Ansi.RESET + " the combination falls off as you "
              + "slowly lift the lid.\n", true);
      Container trunk = curWall.getContainer("trunk");
      trunk.setInspectDescript("");
      trunk.addItem(new Item("mirror",
          "A compact " + Ansi.ITEM + "mirror" + Ansi.RESET + " blinds you as it "
              + "catches the light inside the trunk. ", EntityUseType.MIRROR, true));
      trunk.addItem(new Item("artifact",
          "Under some old papers, you pause as you notice a golden metal " + Ansi.ITEM + "artifact" 
          + Ansi.RESET + " in the shape of a triangle.", EntityUseType.ARTIFACT, true));
      return true;
    }
    return false;
  }
}