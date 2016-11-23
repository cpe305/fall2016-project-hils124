package abandoned.game;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class to run the game.
 * 
 * @author hils124
 */
public class Main {
  public static final House house = GameBuilder.initGame();
  
  private Main() {
    throw new IllegalAccessError("Main class");
  }

  /**
   * Main driver method.
   * 
   * @param args - given arguments
   * @throws Exception
   * 
   */
  public static void main(String[] args) throws Exception {
    // clears terminal window
    Print.printString("\033[H\033[2J", false);
    System.out.flush();
    try (BufferedReader br = new BufferedReader(new FileReader("resources/title.txt"))) {
      String line = null;
      while ((line = br.readLine()) != null) {
        Print.printString(line, false);
      }
    }
    Player player = new Player(house);
    player.addItem(new Item("matches", "", EntityUseType.MATCH, true));
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    Print.printString("Welcome to Abandoned.\n", true);
    while (!validResponse) {
      Print.printString("Would you like to start a new game (yes/no)?\n", true);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          startGame(player);
          break;
        }
        case "no": {
          validResponse = true;
          break;
        }
        default: {
          Print.printString("Invalid response.\n", true);
          break;
        }
      }
    }
    scanner.close();
  }

  /**
   * Initiates game state.
   * 
   * @param player
   *          - current player
   * @throws Exception
   * 
   */
  public static void startGame(Player player) throws Exception {
    Print.printString(
        "\n\nYou slowly open your eyes as you notice a dull pain in the side of your head.\n",
        true);
    Print.printString(
        "You are surrounded by darkness as you realize you have no idea where you are or "
            + "how you got there.\n",
        true);
    Print.printString("Type HELP to view commands.\n", false);
    Scanner scanner = new Scanner(System.in);
    boolean done = false;
    while (!done && scanner.hasNextLine()) {
      String option = scanner.nextLine();
      done = optionParser(option.toLowerCase(), player);
    }
    scanner.close();
  }

  /**
   * Displays help menu.
   * @throws InterruptedException - for scroll text
   */
  public static void viewHelpMenu() {
    Print.printString("\nCommands\n"
        + "ENTER [\u001B[33mPORTAL\u001B[0m]\n" 
        + "INSPECT [\u001B[32mELEMENT\u001B[0m]\n" 
        + "INTERACT [\u001B[32mELEMENT\u001B[0m]\n" 
        + "TAKE [\u001B[36mITEM\u001B[0m]\n"
        + "TURN [LEFT, RIGHT, AROUND]\n"
        + "USE [\u001B[36mITEM\u001B[0m]\n" 
        + "VIEW INVENTORY\n"
        + "QUIT\n", false);
  }

  /**
   * Processes player commands.
   * 
   * @param option - player's chosen action
   * @param player - current player
   * @return if player is done
   * @throws Exception
   * 
   */
  public static boolean optionParser(String option, Player player) {
    boolean done = false;
    Scanner lineScanner = new Scanner(option);
    String command = lineScanner.next();
    String command2;
    switch (command) {
      case "enter": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            processEnterPortal(player, command2);
          } else {
            Print.printString("Usage: ENTER [\u001B[33mPORTAL\u001B[0m]", false);
          }
        } else {
          Print.printString("Usage: ENTER [\u001B[33mPORTAL\u001B[0m]", false);
        }
        break;
      }
      case "help": {
        viewHelpMenu();
        break;
      }
      case "inspect": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            processInspectElement(player, command2);
          } else {
            Print.printString("Usage: INSPECT [\u001B[32mELEMENT\u001B[0m]", false);
          }
        } else {
          Print.printString("Usage: INSPECT [\u001B[32mELEMENT\u001B[0m]", false);
        }
        break;
      }
  
      case "take": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            if (!player.getCanSee()) {
              Print.printString("It is too dark. Action cannot be made.", false);
            } else {
              processTakeItem(player, command2);
            }
          } else {
            Print.printString("Usage: TAKE [\u001B[36mITEM\u001B[0m]", false);
          }
        } else {
          Print.printString("Usage: TAKE [\u001B[36mITEM\u001B[0m]", false);
        }
        break;
      }
      case "turn": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            if (!player.getCanSee()) {
              Print.printString("It is too dark. Action cannot be made.", false);
            } else {
              processTurnPlayer(player, command2);
            }
          } else {
            Print.printString("Usage: TURN [LEFT, RIGHT, AROUND]", false);
          }
        } else {
          Print.printString("Usage: TURN [LEFT, RIGHT, AROUND]", false);
        }
        break;
      }
      case "use": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            processItemAction(player, command2);
          } else {
            Print.printString("Usage: USE [\u001B[36mITEM\u001B[0m]", false);
          }
        } else {
          Print.printString("Usage: USE [\u001B[36mITEM\u001B[0m]", false);
        }
        break;
      }
      case "view": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if ("inventory".equals(command2) && !lineScanner.hasNext()) {
            player.displayInventory();
          } else {
            Print.printString("Usage: VIEW INVENTORY", false);
          }
        } else {
          Print.printString("Usage: VIEW INVENTORY", false);
        }
        break;
      }
      case "quit": {
        Print.printString("Quitting...\n", false);
        done = true;
        break;
      }
      default: {
        if (!player.getCanSee()) {
          Print.printString("It is too dark. Action cannot be made.", false);
        } else {
          Print.printString("Action cannot be made.", false);
        }
        break;
      }
    }
    lineScanner.close();
    return done;
  }

  /**
   * Processes a player entering a portal.
   * 
   * @param player - current player
   * @param portalName - selected portal's name
   * 
   */
  public static void processEnterPortal(Player player, String portalName) {
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
  public static void processInspectElement(Player player, String elementName) {
    Wall curWal = player.getCurrentWall();
    for (Container c : curWal.getContainers()) {
      if (c.getName().equals(elementName)) {
        if (c.hasItems()) {
          c.inspect();
        } else {
          Print.printString("Nothing to inspect.", false);
        }
      }
    }
  }

  /**
   * Processes a player using an item.
   * 
   * @param player - current player
   * @param itemName - selected item's name
   * 
   */
  public static void processItemAction(Player player, String itemName) {
    boolean itemFound = false;
    Item chosenItem = null;
    for (Item item : player.getInventory()) {
      if (item.getName().equals(itemName)) {
        itemFound = true;
        chosenItem = item;
      }
    }
    if (itemFound) {
      boolean success = player.useItem(chosenItem);
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
  public static void processTakeItem(Player player, String itemName) {
    boolean itemFound = false;
    for (Item item : player.getCurrentWall().getItems()) {
      if (item.getName().equals(itemName)) {
        itemFound = true;
        player.addItem(item);
        player.getCurrentWall().removeItem(item);
        break;
      }
    }
    for (Container c : player.getCurrentWall().getContainers()) {
      if (c.getInspected()) {
        for (Item item : c.getItems()) {
          if (item.getName().equals(itemName)) {
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
  public static void processTurnPlayer(Player player, String direction) {
    switch (direction) {
      case "left": {
        player.turnLeft();
        break;
      }
      case "right": {
        player.turnRight();
        break;
      }
      case "around": {
        player.turnAround();
        break;
      }
      default: {
        Print.printString("Action cannot be made.", false);
        break;
      }
    }
  }
}
