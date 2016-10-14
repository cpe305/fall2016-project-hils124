
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		LinkedList<Item> inventory = new LinkedList<Item>();
		Player player = new Player();
		Scanner scanner = new Scanner(System.in);
		boolean validResponse = false;
		scrollText("Welcome to Abandoned.\n");
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
		System.out.println("Type HELP to view commands.");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String option = scanner.nextLine();
			optionParser(option.toLowerCase(), player);
		}
	}
	
	public static void quitGame() {
		
	}
	
	public static  void viewHelpMenu() {
		System.out.println("\nKey Functions");
		System.out.println("'⬅' - view left wall");
		System.out.println("'➡' - view right wall");
		System.out.println("'⬆' - view front wall");
		System.out.println("'⬇' - view back wall");
		System.out.println("\nCommands");
		System.out.println("INSPECT [ELEMENT]");
		System.out.println("TAKE [ITEM]");
		System.out.println("USE [ITEM]");
		System.out.println("VIEW INVENTORY");	
	}
	
	public static void optionParser(String option, Player player) {
		Scanner lineScanner = new Scanner(option);
		String command = lineScanner.next();
		switch(command) {
			case "help": {
				viewHelpMenu();
				break;
			}
			case "view": {
				if (lineScanner.hasNext()) {
					String command2 = lineScanner.next().toLowerCase();
					if (command2.equals("inventory")) {
						displayInventory(player.getInventory());
					}
				}
				break;
			}
			default: {
				System.out.println("Action cannot be made.");
				break;
			}
		}
	}
	
	public static void displayInventory(ArrayList<Item> inventory) {
		if (inventory.size() < 1) {
			System.out.println("Your inventory is empty.");
		}
		else {
			
		}
	}
	
	
}


