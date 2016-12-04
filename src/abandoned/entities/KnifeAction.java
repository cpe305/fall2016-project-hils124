package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.House;
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
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You reach high into the branches with the broom and gently knock the "
          + "object down. The object appears to be a spare \u001B[36mhandle\u001B[0m "
          + "to something.\n", true);
      Container painting = curWall.getContainer("painting");
      painting.setInspectDescript(
          "The painting shows a faded garden with craggly trees and a small pond.");
      Container envelope = new Container("envelope", "You see an envelope on the ground.",
          new ArrayList<Item>(),"");
      envelope.addItem(new Item("letter", "You open the envelope and find a "
          + "\u001B[36mletter\u001B[0m inside. You read the following", EntityUseType.LETTER, 
          true));
      curWall.addContainer(envelope);
      return true;
    }
    return false;
  }
}