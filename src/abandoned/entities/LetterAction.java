package abandoned.entities;

import abandoned.game.Player;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

/**
 * Class to model a letter's action when used.
 * 
 * @author hils124
 */
public class LetterAction implements EntityAction {
  @Override
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) {
    if (item.getUseType().getWall().equals(curWall.getName())) {
      Print.printString(
          "You slowly plunk the notes that match the uppercase letters in the letter you read. "
          + "When you hit the last 'A' key, you hear the house groan as the wall to your left "
          + "starts to move.", true);
      Wall eastWall = curRoom.getEastWall();
      Container fireplace = eastWall.getContainer("fireplace");
      fireplace.setDescription("You see a \u001B[32mfireplace\u001B[0m with a gray brick border. "
          + "The back wall of the fireplace seems to have moved.");
      fireplace.setInspectDescript("You peer into the fireplace and notice a dark passage way "
          + "where the wall used to be. If it wasn't so dark in there, you might be able to "
          + "explore it.");
      fireplace.addItem(new Item("passage", "", null, false));
      return true;
    }
    return false;
  }
}