import java.util.ArrayList;

public class Matches implements Item {
	private String itemName = "matches";

	public String getItemName() {
		return this.itemName;
	}
	
	public ArrayList<Item> pickUpItem(ArrayList<Item> inventory) {
		inventory.add(this);
		return inventory;
	}

	public ArrayList<Item> useItem(ArrayList<Item> inventory) {
		inventory.remove(this);
		return inventory;
	}
}
