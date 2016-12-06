package abandoned.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class to run the game.
 * 
 * @author hils124
 */
public class Main {
  
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
    try {
      processGameState();
    } catch (Exception ex) {
      Logger logger = Logger.getLogger("GameState");
      logger.log(Level.SEVERE, "Problem starting the game.", ex); 
    }
  }

  /**
   * Initiates game state.
   * @throws IOException 
   * 
   */
  public static void processGameState() throws IOException {
    Print.printString("Welcome to Abandoned.\n", true);
    if (new File("resources/saveHouse.json").isFile()) {
      processLoadGame();
    } else {
      processNewGame(false);
    }
  }
  
  /**
   * Start process of creating a new game.
   * @param oldGame - true if there is an existing saved game
   * @throws IOException 
   * 
   */
  public static void processNewGame(boolean oldGame) throws IOException { 
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
            File playerFile = new File("resources/savePlayer.json");
            boolean success1 = houseFile.delete();
            boolean success2 = playerFile.delete();
            if (!success1 || !success2) {
              throw new IOException();
            }
          }
          GlobalHouse.initializeHouse(true);
          GlobalPlayer.initializePlayer(true);
          startGame(true);
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
   * @throws Exception 
   * 
   */
  public static void processLoadGame() throws IOException {
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    while (!validResponse ) {
      Print.printString("Would you like to load your previous game? (yes/no): ", false);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          GlobalHouse.initializeHouse(false);
          GlobalPlayer.initializePlayer(false);
          startGame(false);
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
   * @param newGame - true if player is starting new game
   * @throws Exception 
   * 
   */
  public static void startGame(boolean newGame) {
    if (newGame) {
      Print.printString(GlobalPlayer.get().getCurrentRoom().getDescription(), true);
      GlobalPlayer.get().getCurrentRoom().setDescription("");
    }
    GlobalPlayer.get().getCurrentWall().describe();
    Print.printString("(Type HELP to view commands)\n", false);
    boolean done = false;
    Scanner scanner = new Scanner(System.in);
    while (!done && scanner .hasNextLine()) {
      String option = scanner.nextLine();
      done = optionParser(option.toLowerCase());
    }
    scanner.close();
  }
  
  /**
   * Ends game state.
   */
  public static void quitGame() {
    Scanner scanner = new Scanner(System.in);
    boolean validResponse = false;
    while (!validResponse) {
      Print.printString("Save game? (yes/no): ", false);
      String answer = scanner.next();
      switch (answer) {
        case "yes": {
          validResponse = true;
          saveGame();
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
   * 
   */
  public static void saveGame() {
    boolean result = GameSaver.saveGame();
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
   * @return if player is done
   * 
   */
  public static boolean optionParser(String option) {
    boolean done = false;
    Scanner lineScanner = new Scanner(option);
    String command = lineScanner.next();
    String command2;
    switch (command) {
      case "enter": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            PlayerActions.enterPortal(command2);
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
            PlayerActions.inspectElement(command2);
          } else {
            Print.printString("Usage: INSPECT [\u001B[32mELEMENT\u001B[0m]", false);
          }
        } else {
          Print.printString("Usage: INSPECT [\u001B[32mELEMENT\u001B[0m]", false);
        }
        break;
      }
      case "save": {
        saveGame();
        break;
        
      }
  
      case "take": {
        if (lineScanner.hasNext()) {
          command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            PlayerActions.takeItem(command2);
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
            PlayerActions.turnPlayer(command2);
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
            PlayerActions.itemAction(command2);
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
            GlobalPlayer.get().displayInventory();
          } else {
            Print.printString("Usage: VIEW INVENTORY", false);
          }
        } else {
          Print.printString("Usage: VIEW INVENTORY", false);
        }
        break;
      }
      case "quit": {
        quitGame();
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
