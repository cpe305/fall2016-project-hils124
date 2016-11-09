package abandoned.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import abandoned.entities.Item;
import abandoned.entities.EntityUseType;
import abandoned.house.House;

public class Main {
  public static final House house = GameBuilder.initGame();
  
  public static void main(String[] args) throws Exception {
//    Runtime.getRuntime().exec("/usr/bin/open -a Terminal .");
    System.out.print("\033[H\033[2J");
    System.out.flush();
    try (BufferedReader br = new BufferedReader(new FileReader("resources/title.txt"))) {
      String line = null;
      while ((line = br.readLine()) != null) {
          System.out.println(line);
      }
    }
    Player player = new Player(house);
    player.addItem(new Item("matches", EntityUseType.MATCH, true));
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
  }

  public static void scrollText(String str) throws Exception {
    for (int i = 0; i < str.length(); i++) {
      System.out.print(str.charAt(i));
      Thread.sleep(50);
    }
  }

  public static void startGame(Player player) throws Exception {
    System.out.println("\n\n" + player.getCurrentWall().getName());
    scrollText("You slowly open your eyes as you notice a dull pain in the side of your head.\n");
    scrollText("You are surrounded by darkness as you realize you have no idea where you are or how you got there.\n");
    System.out.println("Type HELP to view commands.\n");
    Scanner scanner = new Scanner(System.in);
    boolean done = false;
    while (!done && scanner.hasNextLine()) {
      String option = scanner.nextLine();
      done = optionParser(option.toLowerCase(), player);
    }
  }

  public static void quitGame() {

  }

  public static void viewHelpMenu() {
    System.out.println("\nCommands");
    System.out.println("INSPECT [\u001B[32mELEMENT\u001B[0m]");
    System.out.println("TAKE [\u001B[36mITEM\u001B[0m]");
    System.out.println("TURN [LEFT, RIGHT, AROUND]");
    System.out.println("USE [\u001B[36mITEM\u001B[0m]");
    System.out.println("VIEW INVENTORY");
    System.out.println("QUIT\n");
  }

  public static boolean optionParser(String option, Player player) {
    boolean done = false;
    Scanner lineScanner = new Scanner(option);
    String command = lineScanner.next();
    switch (command) {
      case "help": {
        viewHelpMenu();
        break;
      }
      case "take": {
        if (lineScanner.hasNext()) {
          String command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext() && !lineScanner.hasNext()) {
            processTakeItem(player, command2);
          }
        }
        break;
      }
      case "turn": {
        if (lineScanner.hasNext()) {
          String command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext() && !lineScanner.hasNext()) {
            processTurnPlayer(player, command2);
          }
        }
        break; 
      }
      case "use": {
        if (lineScanner.hasNext()) {
          String command2 = lineScanner.next().toLowerCase();
          if (!lineScanner.hasNext()) {
            processItemAction(player, command2);
          }
        }
        break;
      }
      case "view": {
        if (lineScanner.hasNext()) {
          String command2 = lineScanner.next().toLowerCase();
          if (command2.equals("inventory") && !lineScanner.hasNext()) {
            displayInventory(player.getInventory());
          }
        }
        break;
      }
      case "quit": {
        System.out.println("Quitting...");
        done = true;
        break;
      }
      default: {
        System.out.println("Action cannot be made.");
        break;
      }
    }
    return done;
  }

  public static void displayInventory(ArrayList<Item> inventory) {
    if (inventory.size() < 1) {
      System.out.println("Your inventory is empty.");
    } else {
      for (int i = 0; i < inventory.size(); i++) {
        System.out.println("- \u001B[36m" + inventory.get(i).getName() + "\u001B[0m");
      }
      System.out.println();
    }
  }

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
      player.useItem(chosenItem);
    } else {
      System.out.println("No such item to use.");
    }
  }

  public static void processTakeItem(Player player, String itemName) {
    // boolean itemFound = false;
    // Item chosenItem = null;
    // for (Item item: player.getInventory()) {
    // if (item.getName().equals(itemName)) {
    // itemFound = true;
    // chosenItem = item;
    // }
    // }
    // if (itemFound) {
    // player.addItem(chosenItem);
    // }
    // else {
    // System.out.println("No such item to take.");
    // }
  }
  
  public static void processTurnPlayer(Player player, String direction) {
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
