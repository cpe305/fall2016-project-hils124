package abandoned.entities.test;

import abandoned.entities.EntityUseType;
import abandoned.entities.Item;
import abandoned.game.GameLoader;
import abandoned.game.GameLoader;
import abandoned.game.Player;
import abandoned.house.Container;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore public class ScissorsActionTest {
  private static House house;
  private static Player player;
  private static Item scissors;
  
  static {
    house = GameLoader.loadHouse();
    player = new Player(house);
    scissors = new Item("scissors", "You see scissors.", EntityUseType.SCISSORS,
        true);
    player.addItem(scissors);
  }

  @Test
  public void testIncorrectActionRoom() {
    Room curRoom = house.getRoom("kitchen");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(scissors, house);
    Assert.assertNotNull(player.getItem("scissors"));
  }
  
  @Test
  public void testIncorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curRoom.getEastWall());
    player.useItem(scissors, house);
    Assert.assertNotNull(player.getItem("scissors"));
  }
  
  @Test
  public void testCorrectActionWall() {
    Room curRoom = house.getRoom("garden");
    Wall curWall = curRoom.getSouthWall();
    player.setCurrentRoom(curRoom);
    player.setCurrentWall(curWall);
    player.useItem(scissors, house);
    Assert.assertNull(player.getItem("scissors"));
    Assert.assertNotNull(curWall.getItem("key"));
    Container chimes = curWall.getContainer("chimes");
    Assert.assertEquals("", chimes.getInspectDescript());
    player.addItem(scissors);
  }
}