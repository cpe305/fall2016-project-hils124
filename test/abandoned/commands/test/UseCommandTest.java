package abandoned.commands.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.Main;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Container;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class UseCommandTest extends AbandonedTest {
  private static Item handle;
  private static Item pin;
  
  static {
    handle = new Item("handle", "this is a handle", EntityUseType.HANDLE, true);
    pin = new Item("pin", "this is a pin", EntityUseType.PIN, true);
  }
  
  @Test
  public void testUseHandle() {
    player.addItem(handle);
    Room kitchen = house.getRoom("kitchen");
    player.setCurrentRoom(kitchen);
    Wall curWall = kitchen.getEastWall();
    player.setCurrentWall(curWall);
    final Container cabinet = curWall.getContainer("cabinet");
    Main.optionParser("take bucket");
    Assert.assertNull(player.getItem("bucket"));
    Main.optionParser("use handle");
    Assert.assertNull(player.getItem("handle"));
    Main.optionParser("take knife");
    Assert.assertNull(player.getItem("knife"));
    Main.optionParser("inspect cabinet");
    Main.optionParser("take knife");
    Assert.assertNotNull(player.getItem("knife"));
    player.removeItem(player.getItem("knife"));
    player.removeItem(handle);
  }
  
  @Test
  public void testUseBroom() {
    player.addItem(pin);
    Room bedroom = house.getRoom("bedroom");
    player.setCurrentRoom(bedroom);
    Wall curWall = bedroom.getWestWall();
    player.setCurrentWall(curWall);
    final Container desk = curWall.getContainer("desk");
    Assert.assertTrue(curWall.getItems().size() == 0);
    Main.optionParser("take scissors");
    Assert.assertNull(player.getItem("scissors"));
    Main.optionParser("use pin");
    Assert.assertNull(player.getItem("scissors"));
    Main.optionParser("take scissors");
    Assert.assertNull(player.getItem("scissors"));
    Main.optionParser("inspect desk");
    Main.optionParser("take scissors");
    Assert.assertNotNull(player.getItem("scissors"));
    player.removeItem(player.getItem("scissors"));
    player.removeItem(pin);
  }

}