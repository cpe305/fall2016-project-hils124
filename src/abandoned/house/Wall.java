package abandoned.house;

import abandoned.entities.Item;
import abandoned.game.Main;

import java.util.ArrayList;


/**
 * Class to model a wall.
 * @author hils124
 */
public class Wall {
  private String name;
  private String direction;
  private ArrayList<Container> containers;
  private ArrayList<Item> items;
  private Portal portal;

  /**
   * Empty constructor for JSON serializing.
   */
  public Wall() {
  }

  /**
   * Public constructor.
   * @param name - wall name
   * @param direction - wall direction according to its room
   * @param containers - list of containers on the wall
   * @param items - list of items on the wall
   * @param portal - portal on the wall. Null if there isn't one
   */
  public Wall(String name, String direction, ArrayList<Container> containers, ArrayList<Item> items,
      Portal portal) {
    this.name = name;
    this.direction = direction;
    this.containers = containers;
    this.items = items;
    this.portal = portal;
  }

  public String getName() {
    return this.name;
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
  
  public Container getContainer(String name) {
    Container container = null;
    for (Container c: containers) {
      if (name.equals(c.getName())) {
        container = c;
      }
    }
    return container;
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

  /**
   * Prints all wall's container and item descriptions. 
   * @throws Exception
   * 
   */
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
