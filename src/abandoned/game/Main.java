package abandoned.game;

import abandoned.commands.Initialize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to run the game.
 * 
 * @author hils124
 */
public class Main {
  
  private Main() {
    throw new IllegalAccessError("Main class");
  }
  
  private static boolean gameEnded = false;

  /**
   * Main driver method.
   * 
   * @param args - given arguments
   * @throws IOException
   * 
   */
  public static void main(String[] args) throws IOException {
    // clears terminal window
    Print.printString(Ansi.CLEAR, false);
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
   * @throws IOException 
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
    while (!gameEnded && !done && scanner.hasNextLine()) {
      String option = scanner.nextLine();
      done = optionParser(option.toLowerCase());
    }
    scanner.close();
  }  
  
  /**
   * Ends game state.
   * @throws IOException - if file not found
   */
  public static void endGame() throws IOException {
    File houseFile = new File("resources/saveHouse.json");
    File playerFile = new File("resources/savePlayer.json");
    boolean success1 = houseFile.delete();
    boolean success2 = playerFile.delete();
    if (!success1 || !success2) {
      throw new IOException();
    }
    gameEnded = true;
  }

  /**
   * Processes player commands.
   * 
   * @param option
   *          - player's chosen action
   * @return if player is done
   * 
   */
  public static boolean optionParser(String option) {
    Scanner lineScanner = new Scanner(option);
    boolean done = false;
    Pattern twoWordPattern = Pattern.compile("^([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*$");
    Matcher twoWordMatcher = twoWordPattern.matcher(option);
    Pattern oneWordPattern = Pattern.compile("^([a-zA-Z]+)\\s*$");
    Matcher oneWordMatcher = oneWordPattern.matcher(option);
    String command;
    if (twoWordMatcher.find()) {
      command = lineScanner.next();
      String name = lineScanner.next();
      Initialize.cmdProcess.setProcess(name);
      switch (command) {
        case "describe": {
          Initialize.invoker.executeCommand(Initialize.describeCmd);
          break;
        }
        case "enter": {
          Initialize.invoker.executeCommand(Initialize.enterCmd);
          break;
        }
        case "inspect": {
          Initialize.invoker.executeCommand(Initialize.inspectCmd);
          break;
        }
        case "take": {
          Initialize.invoker.executeCommand(Initialize.takeCmd);
          break;
        }
        case "turn": {
          Initialize.invoker.executeCommand(Initialize.turnCmd);
          break;
        }
        case "use": {
          Initialize.invoker.executeCommand(Initialize.useCmd);
          break;
        }
        case "view": {
          Initialize.invoker.executeCommand(Initialize.viewCmd);
          break;
        }
        default: {
          Initialize.invoker.executeCommand(Initialize.badCmd);
          break;
        }
      }
    } else if (oneWordMatcher.find()) {
      command = lineScanner.next();
      Initialize.cmdProcess.setProcess(command);
      switch (command) {
        case "help": {
          Initialize.invoker.executeCommand(Initialize.helpCmd);
          break;
        }
        case "save": {
          Initialize.invoker.executeCommand(Initialize.saveCmd);
          break;
        }
        case "quit": {
          Initialize.invoker.executeCommand(Initialize.quitCmd);
          done = true;
          break;
        }
        default: {
          Initialize.invoker.executeCommand(Initialize.badCmd);
          break;
        }
      }
    } else {
      Initialize.invoker.executeCommand(Initialize.badCmd);
    }
    lineScanner.close();
    return done;
  }
}
