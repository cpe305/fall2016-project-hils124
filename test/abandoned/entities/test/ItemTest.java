package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.test.AbandonedTest;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest extends AbandonedTest {
  private static Item item;
  
  static {
    item = new Item("handle", "You see a handle underneath the bed.", EntityUseType.HANDLE, true);
  }
  
  @Test
  public void testCreateItem() {
    Assert.assertEquals("handle", item.getName());
  }
  
  @Test
  public void testDescription() {
    Assert.assertEquals("You see a handle underneath the bed.", item.getDescription());
    item.setDescription("New description");
    Assert.assertEquals("New description", item.getDescription()); 
  }
  
  public void testUseType() {
    Assert.assertEquals(EntityUseType.HANDLE, item.getUseType());
  }
  
  public void testTakeable() {
    Assert.assertTrue(item.getIsTakeable());
  }
}
