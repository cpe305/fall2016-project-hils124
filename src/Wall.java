import java.util.ArrayList;

public class Wall {
	ArrayList<Entity> entities;
	String name;
	
	public Wall(ArrayList<Entity> entities, String name) {
		this.entities = entities;
		this.name = name;	
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public void getInitialDescription(String description) {
		System.out.println(description);
	}
}
