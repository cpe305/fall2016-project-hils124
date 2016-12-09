package abandoned.commands.test;

import abandoned.game.Main;
import abandoned.game.test.AbandonedTest;
import abandoned.house.Room;
import abandoned.house.Wall;

import org.junit.Assert;
import org.junit.Test;

public class TurnCommandTest extends AbandonedTest {
  
  @Test
  public void testTurningLeftRight() {
    Room attic = house.getRoom("attic");
    player.setCurrentRoom(attic);
    Wall curWall = attic.getEastWall();
    player.setCurrentWall(curWall);
    Main.optionParser("turn left");
    Main.optionParser("help");
    Main.optionParser("describe wall");
    Assert.assertEquals("n", player.getCurrentWall().getDirection());
    Main.optionParser("turn right");
    Assert.assertEquals("e", player.getCurrentWall().getDirection());
  }
  
  @Test
  public void testTurningAround() {
    Room library = house.getRoom("library");
    player.setCurrentRoom(library);
    Wall curWall = library.getEastWall();
    player.setCurrentWall(curWall);
    Main.optionParser("turn around");
    Main.optionParser("help");
    Assert.assertEquals("w", player.getCurrentWall().getDirection());
    Main.optionParser("turn around");
    Assert.assertEquals("e", player.getCurrentWall().getDirection());
  }
  
  @Test
  public void incorrectTurning() {
    Room library = house.getRoom("library");
    player.setCurrentRoom(library);
    Wall curWall = library.getEastWall();
    player.setCurrentWall(curWall);
    Main.optionParser("turn l");
    Main.optionParser("help");
    Main.optionParser("describe wall");
    Assert.assertEquals("e", player.getCurrentWall().getDirection());
    Main.optionParser("turn around");
    Assert.assertEquals("w", player.getCurrentWall().getDirection());
    Main.optionParser("turn r");
    Assert.assertEquals("w", player.getCurrentWall().getDirection());
  }

}