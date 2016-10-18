public class Item extends Entity {
	public Item(String name) {
		super(name);
	}
	
	public void use() {
		System.out.println("Using " + this.getName() + "...");
	}
}
