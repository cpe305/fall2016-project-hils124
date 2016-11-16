package abandoned.house;
import java.util.ArrayList;

import abandoned.entities.Item;
import abandoned.game.Main;

public class Container {
  private String name;
  private String description;
  private boolean inspected;
  private ArrayList<Item> items;

  public Container() {
  }

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
    return items.size() != 0;
  }
  
  public void inspect() throws Exception {
    inspected = true;
    for (Item i: items) {
      Main.scrollText(i.getDescription());
    }
    System.out.println("\n");
  }
}
