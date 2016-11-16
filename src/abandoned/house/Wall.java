package abandoned.house;

import abandoned.entities.Item;
import abandoned.game.Main;

import java.util.ArrayList;


public class Wall {
  private String name;
  private String direction;
  private ArrayList<Container> containers;
  private ArrayList<Item> items;
  private Portal portal;
  // ArrayList<Entity> entities;

  public Wall() {
  }

  public Wall(String name, String direction, ArrayList<Container> containers, ArrayList<Item> items, Portal portal) {
    this.name = name;
    this.direction = direction;
    this.containers = containers;
    this.items = items;
    this.portal = portal;
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
  
  public boolean hasPortal() {
    return this.portal != null;
  }
  
  public Portal getPortal() {
    return this.portal;
  }
  
  public void addItem(Item item) {
    this.items.add(item);
  }
  
  public void removeItem(Item item) {
    this.items.remove(item);
  }

  public void describe() throws Exception {
    System.out.println("\n" + getName());
    for (Container c : containers) {
      Main.scrollText(c.getDescription());
    }
    for (Item i : items) {
      Main.scrollText(i.getDescription());
    }
    if (portal != null) {
      Main.scrollText(portal.getDescription());
    }
    System.out.println("\n");
  }
}
