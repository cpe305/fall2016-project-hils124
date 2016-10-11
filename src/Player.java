import java.util.ArrayList;

public class Player {
	private ArrayList<Item> inventory;
	private String location;
	
	public Player() {
		inventory = new ArrayList<Item>();
	}
	
	public void useItem(Item item) {
		inventory.remove(item);
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
}
