package abandoned.house;
import java.util.ArrayList;

import abandoned.entities.Item;

public class Container {
  private String name;
  private ArrayList<Item> items;

  public Container() {
  }

  public Container(String name, ArrayList<Item> items) {
    this.name = name;
    this.items = items;
  }

  public String getName() {
    return this.name;
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
}
