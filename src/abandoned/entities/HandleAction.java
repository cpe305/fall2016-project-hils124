package abandoned.entities;

import abandoned.game.Main;
import abandoned.game.Player;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

public class HandleAction implements EntityAction {
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall)
      throws Exception {
    if ("KITCHEN - EAST".equals(curWall.getName())) {
      Main.scrollText(
          "The handle fits into the \u001B[32mcupboard\u001B[0m and you slowly open it.");
      Container cupboard = curWall.getContainer("cupboard");
      cupboard.setDescription("You see a dusty \u001B[32mcupboard\u001B[0m with an open drawer.");
      cupboard.addItem(new Item("key",
          "There is a rusty, small \u001B[36mkey\u001B[0m inside the cupboard",
          EntityUseType.KEY, true));
      return true;
    }
    return false;
  }
}