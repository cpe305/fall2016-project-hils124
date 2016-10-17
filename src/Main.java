
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Player player = new Player();
		player.addItem(new Item("matches"));
		Scanner scanner = new Scanner(System.in);
		boolean validResponse = false;
		scrollText("\nWelcome to Abandoned.\n");
		while (!validResponse) {
			scrollText("Would you like to start a new game (yes/no)?\n");
			String answer = scanner.next();
			switch(answer) {
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
		scrollText("\n\nYou slowly open your eyes as you notice a dull pain in the side of your head.\n");
		scrollText("You are surrounded by darkness as you realize you have no idea where you are or how you got there.\n");
		System.out.println("Type HELP to view commands.\n");
		Scanner scanner = new Scanner(System.in);
		boolean done = false;
		while(!done && scanner.hasNextLine()) {
			String option = scanner.nextLine();
			done = optionParser(option.toLowerCase(), player);
		}
	}
	
	public static void quitGame() {
		
	}
	
	public static  void viewHelpMenu() {
		System.out.println("\nCommands");
		System.out.println("INSPECT [ELEMENT]");
		System.out.println("TAKE [ITEM]");
		System.out.println("TURN [LEFT, RIGHT, BACK, FORWARD]");
		System.out.println("USE [ITEM]");
		System.out.println("VIEW INVENTORY");
		System.out.println("QUIT\n");
	}
	
	public static boolean optionParser(String option, Player player) {
		boolean done = false;
		Scanner lineScanner = new Scanner(option);
		String command = lineScanner.next();
		switch(command) {
			case "help": {
				viewHelpMenu();
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
		}
		else {
			for (int i = 0; i < inventory.size(); i++) {
				System.out.println("- " + inventory.get(i).getName());
			}
			System.out.println();
		}
	}
	
	public static void processItemAction(Player player, String itemName) {
		System.out.println("Item: " + itemName);
	}
}


