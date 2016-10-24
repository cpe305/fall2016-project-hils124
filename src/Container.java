import java.util.ArrayList;

public class Container extends Entity {
	private ArrayList<Item> items;
	
	public Container() {}
	
	public Container(String name, ArrayList<Item> items) {
		super(name);
		this.items = items;
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
}
