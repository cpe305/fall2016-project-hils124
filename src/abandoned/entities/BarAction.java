package abandoned.entities;

import abandoned.game.Ansi;
import abandoned.game.GlobalHouse;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;


/**
 * Class to model a pry bar's action when used.
 * 
 * @author hils124
 */
public class BarAction implements EntityAction {
  
  @Override
  public boolean use(Item item, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString("You use the pry bar to gently loosen the discolored tile until "
          + "it falls to the ground. Inside, you a see a glowing red button. Hesitantly, "
          + "you press it.\n\n **DRAINING SOUNDS**\n\nThe sound seemed to come from "
          + "above.\n\n", true);
      Container tile = curWall.getContainer("tile");
      tile.setDescription("You see a small hole in the wall and a discolored tile lying "
          + "on the floor.");
      tile.setInspectDescript("");
      Room garden = GlobalHouse.get().getRoom("garden");
      Wall southWall = garden.getWestWall();
      Container pond = southWall.getContainer("pond");
      pond.setDescription("You notice an empty \u001B[32mpond\u001B[0m filled with fallen "
          + "leaves and wilted flowers.");
      pond.setInspectDescript("The water has been drained from the pond. That must be"
          + " what the button in the basement did! ");
      pond.addItem(new Item("artifact", "Under a bunch of damp leaves at the bottom of the pond, "
          + "you see another triangular " + Ansi.ITEM + "artifact" + Ansi.RESET + ".", 
          EntityUseType.ARTIFACT, true)); 
      return true;
    }
    return false;
  }
}