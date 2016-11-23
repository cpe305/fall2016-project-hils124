package abandoned.house;

import abandoned.entities.Item;
import abandoned.game.Print;

import java.util.List;

/**
 * Class to model a wall.
 * 
 * @author hils124
 */
public class Wall {
  private String name;
  private String direction;
  private List<Container> containers;
  private List<Item> items;
  private Portal portal;

  /**
   * Empty constructor for JSON serializing.
   */
  public Wall() {
    //empty for JSON serializing
  }

  /**
   * Public constructor.
   * 
   * @param name - wall name
   * @param direction - wall direction according to its room
   * @param containers - list of containers on the wall
   * @param items - list of items on the wall
   * @param portal - portal on the wall. Null if there isn't one
   */
  public Wall(String name, String direction, List<Container> containers, List<Item> items,
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

  public List<Container> getContainers() {
    return this.containers;
  }

  /**
   * Searches for a container on the wall
   * @param name - name of container to find
   * @return Container - container if it was found. Null otherwise
   */
  public Container getContainer(String name) {
    Container container = null;
    for (Container c : containers) {
      if (name.equals(c.getName())) {
        container = c;
      }
    }
    return container;
  }

  public List<Item> getItems() {
    return this.items;
  }

  /**
   * Checks to see if the wall has a portal.
   * @return boolean - true if there is a portal
   */
  public boolean hasPortal() {
    return this.portal != null;
  }

  public Portal getPortal() {
    return this.portal;
  }

  /**
   * Adding item to wall.
   * @param item - item to add
   */
  public void addItem(Item item) {
    this.items.add(item);
  }

  /**
   * Removing item from wall.
   * @param item - item to remove
   */
  public void removeItem(Item item) {
    this.items.remove(item);
  }

  /**
   * Prints all wall's container and item descriptions.
   * 
   * @throws Exception
   * 
   */
  public void describe() throws Exception {
    Print.printString("\n" + getName(), false);
    for (Container c : containers) {
      Print.printString(c.getDescription(), true);
    }
    for (Item i : items) {
      Print.printString(i.getDescription(), true);
    }
    if (portal != null) {
      Print.printString(portal.getDescription(), true);
    }
    Print.printString("\n", false);
  }
}
