package abandoned.game;

import abandoned.entities.Item;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Wall;

/**
 * Class to handle game logic with player actions.
 * 
 * @author hils124
 */
public class PlayerActions {
  
  private PlayerActions() {
    throw new IllegalAccessError("Player Actions class");
  }

  /**
   * Processes a player entering a portal.
   * 
   * @param player - current player
   * @param portalName - selected portal's name
   * @param house - the main house
   * 
   */
  public static void enterPortal(Player player, String portalName, House house) {
    Wall curWal = player.getCurrentWall();
    if (curWal.hasPortal()) {
      Portal portal = curWal.getPortal();
      if (portal.getType().equals(portalName)) {
        player.enter(portal, house);
      }
    }
  }
  
  /**
   * Processes a player inspecting an element.
   * 
   * @param player - current player
   * @param elementName - selected element's name
   * 
   */
  public static void inspectElement(Player player, String elementName) {
    Wall curWall = player.getCurrentWall();
    Container container = curWall.getContainer(elementName);
    if (container != null) {
      if (container.hasItems() || container.getInspectDescript().length() != 0) {
        container.inspect();
      } else {
        Print.printString("Nothing to inspect.", false);
      }
    }
  }
  
  /**
   * Processes a player using an item.
   * 
   * @param player - current player
   * @param itemName - selected item's name
   * @param house - the main house
   * 
   */
  public static void itemAction(Player player, String itemName, House house) {
    Item chosenItem = player.getItem(itemName);
    if (chosenItem != null) {
      boolean success = player.useItem(chosenItem, house);
      if (!success) {
        Print.printString("Cannot use item here.", false);
      }
    } else {
      Print.printString("No such item to use.", false);
    }
  }
  
  /**
   * Processes a player taking an item.
   * 
   * @param player - current player
   * @param itemName - selected item's name
   */
  public static void takeItem(Player player, String itemName) {
    boolean itemFound = false;
    Wall curWall = player.getCurrentWall();
    Item item = curWall.getItem(itemName);
    if (item != null) {
      itemFound = true;
      player.addItem(item);
      curWall.removeItem(item);
    } else {
      for (Container c : curWall.getContainers()) {
        if (c.getInspected()) {
          item = c.getItem(itemName);
          if (item != null && item.getIsTakeable()) {
            itemFound = true;
            player.addItem(item);
            c.removeItem(item);
            break;
          }
        }
      }
    }
    if (!itemFound) {
      Print.printString("No such item to take.", false);
    }
  }
  
  /**
   * Processes a player turning a certain direction.
   * 
   * @param player - current player
   * @param direction - selected direction
   * 
   */
  public static void turnPlayer(Player player, String direction) {
    if ("left".equals(direction)) {
      player.turnLeft();
    } else if ("right".equals(direction)) {
      player.turnRight();
    } else if ("around".equals(direction)) {
      player.turnAround();
    } else {
      Print.printString("Action cannot be made.", false);
    }
  }
}
