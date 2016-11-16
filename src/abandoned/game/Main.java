package abandoned.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import abandoned.entities.Item;
import abandoned.entities.EntityUseType;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Wall;

public class Main {
  public static final House house = GameBuilder.initGame();
  
  public static void main(String[] args) throws Exception {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    try (BufferedReader br = new BufferedReader(new FileReader("resources/title.txt"))) {
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    }
    Player player = new Player(house);
    player.addItem(new Item("matches", "", EntityUseType.MATCH, true));
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    scrollText("Welcome to Abandoned.\n");
    while (!validResponse) {
      scrollText("Would you like to start a new game (yes/no)?\n");
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          startGame(player);
          break;
        }
        case "no": {
          validResponse = true;
          quitGame();
          break;
        }
        default: {
          scrollText("Invalid response.\n");
          break;
        }
      }
    }
    scanner.close();
  }

  public static void scrollText(String str) throws Exception {
    for (int i = 0; i < str.length(); i++) {
      System.out.print(str.charAt(i));
      Thread.sleep(50);
    }
  }

  public static void startGame(Player player) throws Exception {
    scrollText("\n\nYou slowly open your eyes as you notice a dull pain in the side of your head.\n");
    scrollText("You are surrounded by darkness as you realize you have no idea where you are or how you got there.\n");
    System.out.println("Type HELP to view commands.\n");
    Scanner scanner = new Scanner(System.in);
    boolean done = false;
    while (!done && scanner.hasNextLine()) {
      String option = scanner.nextLine();
      done = optionParser(option.toLowerCase(), player);
    }
    scanner.close();
  }

  public static void quitGame() {

  }

  public static void viewHelpMenu() {
    System.out.println("\nCommands");
    System.out.println("ENTER [\u001B[33mPORTAL\u001B[0m]");
    System.out.println("INSPECT [\u001B[32mELEMENT\u001B[0m]");
    System.out.println("INTERACT [\u001B[32mELEMENT\u001B[0m]");
    System.out.println("TAKE [\u001B[36mITEM\u001B[0m]");
    System.out.println("TURN [LEFT, RIGHT, AROUND]");
    System.out.println("USE [\u001B[36mITEM\u001B[0m]");
    System.out.println("VIEW INVENTORY");
    System.out.println("QUIT\n");
  }

  public static boolean optionParser(String option, Player player) throws Exception {
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
          }
          else {
            System.out.println("Usage: ENTER [\u001B[33mPORTAL\u001B[0m]");
          }
        }
        else {
          System.out.println("Usage: ENTER [\u001B[33mPORTAL\u001B[0m]");
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
          }
          else {
            System.out.println("Usage: INSPECT [\u001B[32mELEMENT\u001B[0m]");
          }
        }
        else {
          System.out.println("Usage: INSPECT [\u001B[32mELEMENT\u001B[0m]");
        }
        break;
      }

      case "take": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            if (!player.getCanSee()) {
              System.out.println("It is too dark. Action cannot be made.");
            }
            else {
              processTakeItem(player, command2);
            }
          }
          else {
            System.out.println("Usage: TAKE [\u001B[36mITEM\u001B[0m]");
          }
        }
        else {
          System.out.println("Usage: TAKE [\u001B[36mITEM\u001B[0m]");
        }
        break;
      }
      case "turn": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            if (!player.getCanSee()) {
              System.out.println("It is too dark. Action cannot be made.");
            }
            else {
              processTurnPlayer(player, command2);
            }
          }
          else {
            System.out.println("Usage: TURN [LEFT, RIGHT, AROUND]");
          }
        }
        else {
          System.out.println("Usage: TURN [LEFT, RIGHT, AROUND]");
        }
        break; 
      }
      case "use": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            processItemAction(player, command2);
          }
          else {
            System.out.println("Usage: USE [\u001B[36mITEM\u001B[0m]");
          }
        } 
        else {
          System.out.println("Usage: USE [\u001B[36mITEM\u001B[0m]");
        }
        break;
      }
      case "view": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if ("inventory".equals(command2) && !lineScanner.hasNext()) {
            player.displayInventory();
          }
          else {
            System.out.println("Usage: VIEW INVENTORY");
          }
        }
        else {
          System.out.println("Usage: VIEW INVENTORY");
        }
        break;
      }
      case "quit": {
        System.out.println("Quitting...");
        done = true;
        break;
      }
      default: {
        if (!player.getCanSee()) {
          System.out.println("It is too dark. Action cannot be made.");
        }
        else {
          System.out.println("Action cannot be made.");
        }
        break;
      }
    }
    lineScanner.close();
    return done;
  }
  
  public static void processEnterPortal(Player player, String portalName) throws Exception {
    Wall curWal = player.getCurrentWall();
    if (curWal.hasPortal()) {
      Portal portal = curWal.getPortal();
      if (portal.getType().equals(portalName)) {
        player.enter(portal, house);
      }
    }
  }
  
  public static void processInspectElement(Player player, String elementName) throws Exception {
    Wall curWal = player.getCurrentWall();
    for (Container c : curWal.getContainers()) {
      if (c.getName().equals(elementName)) {
        if (c.hasItems()) {
          c.inspect();
        } else {
          System.out.println("Nothing to inspect.");
        }
      }
    }
  }

  public static void processItemAction(Player player, String itemName) throws Exception {
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
        System.out.println("Cannot use item here.");
      }
    } else {
      System.out.println("No such item to use.");
    }
  }

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
      System.out.println("No such item to take.");
    }
  }
  
  public static void processTurnPlayer(Player player, String direction) throws Exception {
    switch(direction) {
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
        System.out.println("Action cannot be made.");
        break;
      }
    }
  }
}
