package abandoned.house;
import java.util.ArrayList;

public class Wall {
  String name;
  ArrayList<Container> containers;
  ArrayList<Item> items;
  // ArrayList<Entity> entities;

  public Wall() {
  }

  public Wall(String name, ArrayList<Container> containers, ArrayList<Item> items) {
    this.name = name;
    this.containers = containers;
    this.items = items;
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Container> getContainers() {
    return this.containers;
  }

  public ArrayList<Item> getItems() {
    return this.items;
  }

  // public ArrayList<Entity> getEntities() {
  // return this.entities;
  // }
  //
  // public void addEntity(Entity entity) {
  // this.entities.add(entity);
  // }
  //
  // public void removeEntity(Entity entity) {
  // this.entities.remove(entity);
  // }

  public void getInitialDescription(String description) {
    System.out.println(description);
  }
}
