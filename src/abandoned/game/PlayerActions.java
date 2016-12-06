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
   * @param portalName - selected portal's name
   * 
   */
  public static void enterPortal(String portalName) {
    Wall curWal = GlobalPlayer.get().getCurrentWall();
    if (curWal.hasPortal()) {
      Portal portal = curWal.getPortal();
      if (portal.getType().equals(portalName)) {
        GlobalPlayer.get().enter(portal);
      }
    }
  }
  
  /**
   * Processes a player inspecting an element.
   * 
   * @param elementName - selected element's name
   * 
   */
  public static void inspectElement(String elementName) {
    Wall curWall = GlobalPlayer.get().getCurrentWall();
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
   * @param itemName - selected item's name
   * 
   */
  public static void itemAction(String itemName) {
    Item chosenItem = GlobalPlayer.get().getItem(itemName);
    if (chosenItem != null) {
      boolean success = GlobalPlayer.get().useItem(chosenItem);
      if (!success) {
        Print.printString("Cannot use item here.", false);
      }
    } else {
      Print.printString("No such item to use.", false);
    }
  }
  
  /**
   * Processes a player taking an item.

   * @param itemName - selected item's name
   */
  public static void takeItem(String itemName) {
    boolean itemFound = false;
    boolean isArtifact = false;
    if (GlobalPlayer.get().getArtifactCount() > 0) {
      isArtifact = true;
    }
    Wall curWall = GlobalPlayer.get().getCurrentWall();
    Item item = curWall.getItem(itemName);
    if (item != null) {
      itemFound = true;
      if (isArtifact) {
        GlobalPlayer.get().incrementArtifactCount();
        Item artifact = GlobalPlayer.get().getItem("artifact");
        artifact.setName("artifact (" + GlobalPlayer.get().getArtifactCount() + ")");
      } else {
        GlobalPlayer.get().addItem(item);
        curWall.removeItem(item);
      }
    } else {
      for (Container c : curWall.getContainers()) {
        if (c.getInspected()) {
          item = c.getItem(itemName);
          if (item != null && item.getIsTakeable()) {
            itemFound = true;
            if (isArtifact) {
              GlobalPlayer.get().incrementArtifactCount();
              Item artifact = GlobalPlayer.get().getItem("artifact");
              artifact.setName("artifacts");
            } else {
              GlobalPlayer.get().addItem(item);
              c.removeItem(item);
              break;
            }
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
   * @param direction - selected direction
   * 
   */
  public static void turnPlayer(String direction) {
    if ("left".equals(direction)) {
      GlobalPlayer.get().turnLeft();
    } else if ("right".equals(direction)) {
      GlobalPlayer.get().turnRight();
    } else if ("around".equals(direction)) {
      GlobalPlayer.get().turnAround();
    } else {
      Print.printString("Action cannot be made.", false);
    }
  }
}
