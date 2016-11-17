package abandoned.house;

import abandoned.entities.Item;
import abandoned.game.Main;

import java.util.ArrayList;


/**
 * Class to model a container that may hold items.
 * @author hils124
 */
public class Container {
  private String name;
  private String description;
  private boolean inspected;
  private ArrayList<Item> items;

  /**
   * Empty constructor for JSON serializing.
   */
  public Container() {
  }

  /**
   * Public constructor.
   * @param name - container's name
   * @param description - container's description
   * @param items - list of items in the container
   */
  public Container(String name, String description, ArrayList<Item> items) {
    this.name = name;
    this.description = description;
    this.inspected = false;
    this.items = items;
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
  
  public ArrayList<Item> getItems() {
    return this.items;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    items.remove(item);
  }
  
  public boolean hasItems() {
    return !items.isEmpty();
  }
  
  /**
   * Inspecting a container to display its items' descriptions.
   * @throws Exception
   * 
   */
  public void inspect() throws Exception {
    inspected = true;
    for (Item i: items) {
      Main.scrollText(i.getDescription());
    }
    System.out.println("\n");
  }
}
