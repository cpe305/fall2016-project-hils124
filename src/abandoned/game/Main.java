package abandoned.game;

import abandoned.house.House;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to run the game.
 * 
 * @author hils124
 */
public class Main {
  public static House house;
  public static final Scanner scanner = new Scanner(System.in);
  
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
    processGameState();
  }

  /**
   * Initiates game state.
   * 
   */
  public static void processGameState() {
    Print.printString("Welcome to Abandoned.\n", true);
    if (new File("resources/saveHouse.json").isFile()) {
      processLoadGame();
    } else {
      processNewGame(false);
    }
  }
  
  /**
   * Start process of creating a new game.
   * 
   */
  public static void processNewGame(boolean oldGame) {
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    while (!validResponse ) {
      Print.printString("Would you like to start a new game? (yes/no): ", false);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          if (oldGame) {
            File houseFile = new File("resources/saveHouse.json");
            File playerFile = new File("resources/saveHouse.json");
            houseFile.delete();
            playerFile.delete();
          }
          house = GameBuilder.newHouse();
          Player player = new Player(house);
          startGame(player, true);
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
  }
  
  
  /**
   * Starts process of loading a previous game.
   * 
   */
  public static void processLoadGame() {
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    while (!validResponse ) {
      Print.printString("Would you like to load your previous game? (yes/no): ", false);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          house = GameLoader.loadHouse();
          Player player = GameLoader.loadPlayer();
          startGame(player, false);
          break;
        }
        case "no": {
          validResponse = true;
          processNewGame(true);
          break;
        }
        default: {
          Print.printString("Invalid response.\n", false);
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
  public static void startGame(Player player, boolean newGame) {
    if (newGame) {
      Print.printString(player.getCurrentRoom().getDescription(), true);
    }
    player.getCurrentWall().describe();
    Print.printString("(Type HELP to view commands)\n", false);
    boolean done = false;
    while (!done && scanner.hasNextLine()) {
      String option = scanner.nextLine();
      done = optionParser(option.toLowerCase(), player);
    }
  }
  
  /**
   * Ends game state.
   * 
   * @param player - current player
   * 
   */
  public static void quitGame(Player player) {
    boolean validResponse = false;
    while (!validResponse) {
      Print.printString("Save game? (yes/no): ", false);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          saveGame(player);
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
    Print.printString("Quitting...\n", false);
  }
    
  /**
   * Saves game state.
   * 
   * @param player - current player
   * 
   */
  public static void saveGame(Player player) {
    boolean result = GameSaver.saveGame(player);
    if (result) {
      Print.printString("Game saved", false);
    }
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
      case "save": {
        saveGame(player);
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
        quitGame(player);
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
