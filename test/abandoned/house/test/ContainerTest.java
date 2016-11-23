package abandoned.house.test;

import abandoned.entities.Item;
import abandoned.house.Container;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContainerTest {
  private static Container container;
  
  static {
    List<Item> items = new ArrayList<Item>();
    container = new Container("bookcase", "You see a bookcase", items);
  }
  
  @Test
  public void testCreateContainer() {
    Assert.assertEquals(container.getName(), "bookcase");
    Assert.assertEquals(container.getDescription(), "You see a bookcase");
    container.setDescription("You see an empty bookcase");
    Assert.assertEquals(container.getDescription(), "You see an empty bookcase");
  }
  
  @Test
  public void testItemActions() {
    Assert.assertTrue(container.getItems().size() == 0);
    Assert.assertFalse(container.hasItems());
    Item item = new Item();
    container.addItem(item);
    Assert.assertTrue(container.getItems().size() == 1);
    Assert.assertTrue(container.hasItems());
    container.removeItem(item);
    Assert.assertTrue(container.getItems().size() == 0);
    Assert.assertFalse(container.hasItems());
  }
  
  @Test
  public void testInspected() {
    Assert.assertFalse(container.getInspected());
    container.inspect();
    Assert.assertTrue(container.getInspected());
  }
}
