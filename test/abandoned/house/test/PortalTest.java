package abandoned.house.test;

import abandoned.house.Portal;

import org.junit.Assert;
import org.junit.Test;

public class PortalTest {
  private static Portal portal;
  
  static {
    portal = new Portal("bedroomDoor", "door", "bedroom", "westWall", "You see a white door");
  }
  
  @Test
  public void testCreatePortal() {
    Assert.assertEquals(portal.getName(), "bedroomDoor");
    Assert.assertEquals(portal.getType(), "door");
    Assert.assertEquals(portal.getRoomName(), "bedroom");
    Assert.assertEquals(portal.getWallName(), "westWall");
    Assert.assertEquals(portal.getDescription(), "You see a white door");
  }
}
