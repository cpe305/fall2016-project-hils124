package abandoned.house.test;

import abandoned.house.Container;
import abandoned.entities.Item;
import abandoned.house.Wall;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class WallTest {
  private static Wall wall;
  
  static {
    ArrayList<Item> items = new ArrayList<Item>();
    Container c = new Container("bed", "You see a bed", items);
    ArrayList<Container> containers = new ArrayList<Container>();
    containers.add(c);
    wall = new Wall("BEDROOM - WEST", "w", containers, items, null);
  }
  
  @Test
  public void testCreateWall() {
    Assert.assertTrue(wall.hasPortal());
    Assert.assertEquals(wall.getDirection(), "w");
    Assert.assertNull(wall.getPortal());
  }
}
