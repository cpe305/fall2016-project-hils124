package abandoned.house;

import abandoned.entities.Item;
import abandoned.game.Print;

import java.util.List;

/**
 * Class to model a container that may hold items.
 * 
 * @author hils124
 */
public class Container {
  private String name;
  private String description;
  private boolean inspected;
  private String inspectDescript;
  private List<Item> items;

  /**
   * Empty constructor for JSON serializing.
   */
  public Container() {
    //empty for JSON serializing
  }

  /**
   * Public constructor.
   * 
   * @param name - container's name
   * @param description - container's description
   * @param items - list of items in the container
   * @param inspectDescript - extra description when container is inspected
   */
  public Container(String name, String description, List<Item> items, String inspectDescript) {
    this.name = name;
    this.description = description;
    this.inspected = false;
    this.items = items;
    this.inspectDescript = inspectDescript;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getInspected() {
    return this.inspected;
  }
  
  public String getInspectDescript() {
    return this.inspectDescript;
  }
  
  public void setInspectDescript(String descript) {
    this.inspectDescript = descript;
  }

  public List<Item> getItems() {
    return this.items;
  }

  /**
   * Adding item to container's item list.
   * @param item - item to add
   */
  public void addItem(Item item) {
    items.add(item);
  }

  /**
   * Removing item from container's item list.
   * @param item - item to add
   */
  public void removeItem(Item item) {
    items.remove(item);
  }
  
  /**
   * Searches for a item in the container
   * @param name - name of item to find
   * @return Item - item if it was found. Null otherwise
   */
  public Item getItem(String name) {
    Item item = null;
    for (Item i : items) {
      if (name.equals(i.getName())) {
        item = i;
      }
    }
    return item;
  }

  /**
   * Adding item to player's inventory.
   * @return boolean - true if container has items
   */
  public boolean hasItems() {
    return !items.isEmpty();
  }

  /**
   * Inspecting a container to display its items' descriptions.
   * 
   */
  public void inspect() {
    inspected = true;
    Print.printString(this.inspectDescript, true);
    for (Item i : items) {
      Print.printString(i.getDescription(), true);
    }
    Print.printString("\n", false);
  }
}
