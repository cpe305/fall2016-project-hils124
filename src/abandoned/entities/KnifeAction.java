package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import java.util.ArrayList;

/**
 * Class to model a knife's action when used.
 * 
 * @author hils124
 */
public class KnifeAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You use the knife to pry open the painting's frame. An "
          + Ansi.BIN + "envelope" + Ansi.RESET + " falls to the ground.\n\n", true);
      Container painting = curWall.getContainer("painting");
      painting.setInspectDescript(
          "The painting shows a faded garden with craggly trees and a small pond.");
      Container envelope = new Container("envelope", " You see an " + Ansi.BIN + "envelope" 
          + Ansi.RESET + " on the ground.",
          new ArrayList<Item>(),"");
      envelope.addItem(new Item("letter", "You open the envelope and find a "
          + "" + Ansi.ITEM + "letter" + Ansi.RESET + " inside. You read the following", 
          EntityUseType.LETTER, true));
      curWall.addContainer(envelope);
      return true;
    }
    return false;
  }
}