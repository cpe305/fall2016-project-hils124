package abandoned.house;
import java.util.ArrayList;

import abandoned.entities.Item;

public class Wall {
  private String name;
  private String direction;
  private ArrayList<Container> containers;
  private ArrayList<Item> items;
  // ArrayList<Entity> entities;

  public Wall() {
  }

  public Wall(String name, String direction, ArrayList<Container> containers, ArrayList<Item> items) {
    this.name = name;
    this.direction = direction;
    this.containers = containers;
    this.items = items;
  }

  public String getName() {
    return "\033[0;1m" + this.name + "\033[0;0m";
  }
  
  public String getDirection() {
    return this.direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
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
