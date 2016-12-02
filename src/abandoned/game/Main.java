package abandoned.game;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
   * @throws IOException
   * 
   */
  public static void main(String[] args) throws IOException {
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
   * @param player - current player
   * 
   */
  public static void startGame(Player player) {
    Print.printString(player.getCurrentRoom().getDescription(), true);
    player.getCurrentWall().describe();
    Print.printString("(Type HELP to view commands)\n", false);
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
        + "enter [\u001B[33mPORTAL\u001B[0m]\n" 
        + "inspect [\u001B[32mELEMENT\u001B[0m]\n"
        + "take [\u001B[36mITEM\u001B[0m]\n"
        + "turn [LEFT, RIGHT, AROUND]\n"
        + "use [\u001B[36mITEM\u001B[0m]\n" 
        + "view inventory\n"
        + "quit\n", false);
  }

  /**
   * Processes player commands.
   * 
   * @param option - player's chosen action
   * @param player - current player
   * @return if player is done
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
            PlayerActions.enterPortal(player, command2, house);
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
            PlayerActions.inspectElement(player, command2);
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
            PlayerActions.takeItem(player, command2);
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
            PlayerActions.turnPlayer(player, command2);
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
            PlayerActions.itemAction(player, command2);
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
        Print.printString("Action cannot be made.", false);
        break;
      }
    }
    lineScanner.close();
    return done;
  }
}
