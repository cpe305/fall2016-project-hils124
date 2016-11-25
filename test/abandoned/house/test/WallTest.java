package abandoned.house.test;

import abandoned.entities.Item;
import abandoned.house.Container;
import abandoned.house.Portal;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WallTest {
  private static Wall wall;
  
  static {
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Container> containers = new ArrayList<Container>();
    wall = new Wall("BEDROOM - WEST", "w", containers, items, null);
  }
  
  @Test
  public void testCreateWall() {
    Assert.assertEquals(wall.getName(), "BEDROOM - WEST");
  }
  
  @Test
  public void testDirection() {
    Assert.assertEquals(wall.getDirection(), "w");
    wall.setDirection("s");
    Assert.assertNotEquals(wall.getDirection(), "w");
    Assert.assertEquals(wall.getDirection(), "s");
  }
  
  @Test
  public void testWallItemActions() {
    Assert.assertTrue(wall.getItems().size() == 0);
    Item item = new Item();
    wall.addItem(item);
    Assert.assertTrue(wall.getItems().size() == 1);
    wall.removeItem(item);
    Assert.assertTrue(wall.getItems().size() == 0);
  }
  
  @Test
  public void testWallContainerActions() {
    Assert.assertTrue(wall.getContainers().size() == 0);
    Container container = new Container();
    wall.addContainer(container);
    Assert.assertTrue(wall.getContainers().size() == 1);
    wall.removeContainer(container);
    Assert.assertTrue(wall.getContainers().size() == 0);
    ArrayList<Item> items = new ArrayList<Item>();
    Container c1 = new Container("bookcase", "You see a bookcase", items, "");
    Container c2 = new Container("desk", "You see a desk", items, "");
    wall.addContainer(c1);
    wall.addContainer(c2);
    Assert.assertEquals(wall.getContainer("desk"), c2);
    wall.removeContainer(c2);
    Assert.assertNull(wall.getContainer("desk"));
  }

  @Test
  public void testPortal() {
    Assert.assertFalse(wall.hasPortal());
    Assert.assertNull(wall.getPortal());
    Portal portal = new Portal();
    wall.setPortal(portal);
    Assert.assertTrue(wall.hasPortal());
    Assert.assertNotNull(wall.getPortal()); 
  }
}
