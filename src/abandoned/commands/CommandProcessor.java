package abandoned.commands;

import abandoned.entities.Item;
import abandoned.game.GameSaver;
import abandoned.game.GlobalPlayer;
import abandoned.game.Print;
import abandoned.house.Container;
import abandoned.house.Portal;
import abandoned.house.Wall;

import java.util.Scanner;

/**
 * Class to handle each individual command action.
 * @author hils124
 */
public class CommandProcessor {
  private String name;
    
  /**
   * Method to set the process name.
   * @param name - name of process
   */
  public void setProcess(String name) {
    this.name = name;
  }
  
  /**
   * Processes a bad command.
   */
  public void bad() {
    Print.printString("Action cannot be made.\n", false);
  }
  
  /**
   * Processes a bad command.
   */
  public void describe() {
    if ("wall".equals(name)) {
      GlobalPlayer.get().getCurrentWall().describe();
    } else {
      bad();
    }
  }

  /**
   * Processes a player entering a portal.
   */
  public void enter() {
    Wall curWal = GlobalPlayer.get().getCurrentWall();
    if (curWal.hasPortal()) {
      Portal portal = curWal.getPortal();
      if (portal.getType().equals(name)) {
        GlobalPlayer.get().enter(portal);
      }
    } else {
      Print.printString("No such portal to enter.\n", false);
    }
  }
  
  /**
   * Displays help menu.
   */
  public void help() {
    Print.printString("\nCommands\n"
        + "enter [\u001B[33mPORTAL\u001B[0m]\n" 
        + "inspect [\u001B[32mELEMENT\u001B[0m]\n"
        + "take [\u001B[36mITEM\u001B[0m]\n"
        + "turn [LEFT, RIGHT, AROUND]\n"
        + "use [\u001B[36mITEM\u001B[0m]\n" 
        + "view inventory\n"
        + "quit\n", false);
  }
    
  /**
   * Processes a player inspecting an element.
   */
  public void inspect() {
    Wall curWall = GlobalPlayer.get().getCurrentWall();
    Container container = curWall.getContainer(name);
    if (container != null) {
      if (container.hasItems() || container.getInspectDescript().length() != 0) {
        container.inspect();
      } else {
        Print.printString("Nothing to inspect.\n", false);
      }
    } else {
      Print.printString("No such thing to inspect.\n", false);
    }
  }
  
  /**
   * Ends game state.
   */
  public void quit() {
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    while (!validResponse) {
      Print.printString("Save game? (yes/no): ", false);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          save();
          break;
        }
        case "no": {
          validResponse = true;
          break;
        }
        default: {
          Print.printString("Invalid response.\n", false);
          break;
        }
      }
    }
    scanner.close();
    Print.printString("Quitting...\n", false);
  }
  
  /**
   * Saves game state.
   */
  public void save() {
    boolean result = GameSaver.saveGame();
    if (result) {
      Print.printString("Game saved.\n", false);
    }
  }
  
  /**
   * Processes a player taking an item.
   */
  public void take() {
    boolean itemFound = false;
    Wall curWall = GlobalPlayer.get().getCurrentWall();
    Item item = curWall.getItem(name);
    if (item != null) {
      itemFound = true;
      if ("artifact".equals(name)) {
        if (GlobalPlayer.get().getArtifactCount() == 0) {
          GlobalPlayer.get().addItem(item);
        }
        if (GlobalPlayer.get().getArtifactCount() == 1) {
          Item artifact = GlobalPlayer.get().getItem("artifact");
          artifact.setName("artifacts"); 
        } 
        GlobalPlayer.get().incrementArtifactCount();
      } else {
        GlobalPlayer.get().addItem(item);
      }
      curWall.removeItem(item);
    } else {
      for (Container c : curWall.getContainers()) {
        if (c.getInspected()) {
          item = c.getItem(name);
          if (item != null && item.getIsTakeable()) {
            itemFound = true;
            if ("artifact".equals(name)) {
              if (GlobalPlayer.get().getArtifactCount() == 0) {
                GlobalPlayer.get().addItem(item);
              }
              if (GlobalPlayer.get().getArtifactCount() == 1) {
                Item artifact = GlobalPlayer.get().getItem("artifact");
                artifact.setName("artifacts"); 
              } 
              GlobalPlayer.get().incrementArtifactCount();
            } else {
              GlobalPlayer.get().addItem(item);
            }
            c.removeItem(item);
            break;
          }
        }
      }
    }
    if (!itemFound) {
      Print.printString("No such item to take.\n", false);
    }
  }

  
  /**
   * Processes a player turning a certain direction.
   */
  public void turn() {
    if ("left".equals(name)) {
      GlobalPlayer.get().turnLeft();
    } else if ("right".equals(name)) {
      GlobalPlayer.get().turnRight();
    } else if ("around".equals(name)) {
      GlobalPlayer.get().turnAround();
    } else {
      bad();
    }
  }
  
  
  /**
   * Processes a player using an item.
   */
  public void use() {
    Item chosenItem = GlobalPlayer.get().getItem(name);
    if (chosenItem != null) {
      boolean success = GlobalPlayer.get().useItem(chosenItem);
      if (!success) {
        Print.printString("Cannot use item here.\n", false);
      }
    } else {
      Print.printString("No such item to use.\n", false);
    }
  }
  
  /**
   * Processes viewing a player's inventory.
   */
  public void view() {
    if ("inventory".equals(name)) {
      GlobalPlayer.get().displayInventory();
    } else {
      bad();
    }
  }
}
