package abandoned.game;

import abandoned.entities.Item;
import abandoned.house.House;
import abandoned.house.Portal;
import abandoned.house.Room;
import abandoned.house.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a player in the game.
 * @author hils124
 */
public class Player {
  private ArrayList<Item> inventory;
  private Room currentRoom;
  private Wall currentWall;
  private boolean canSee;

  /**
   * Public constructor.
   * @param house - main house
   */
  public Player(House house) {
    inventory = new ArrayList<>();
    this.currentRoom = house.getRoom("bedroom");
    this.currentWall = this.currentRoom.getNorthWall();
    this.canSee = false;
  }

  /**
   * Using an item.
   * @param item - chosen item
   * @return true on success
   * 
   */
  public boolean useItem(Item item) {
    boolean success = item.use(this, item, Main.house, currentRoom, currentWall);
    if (success) {
      inventory.remove(item);
    }
    return success;
  }

  
  /**
   * Adding item to player's inventory.
   * @param item - item to add
   */
  public void addItem(Item item) {
    inventory.add(item);
  }

  public List<Item> getInventory() {
    return inventory;
  }
  
  public void setCurrentRoom(Room room) {
    this.currentRoom = room;
  }
  
  public Room getCurrentRoom() {
    return this.currentRoom;
  }
  
  public void setCurrentWall(Wall wall) {
    this.currentWall = wall;
  }
  
  public Wall getCurrentWall() {
    return this.currentWall;
  }
  
  public void setCanSee(boolean value) {
    this.canSee = value;
  }
  
  public boolean getCanSee() {
    return this.canSee;
  }
  
  /**
   * Displays everything in the player's inventory.
   */
  public void displayInventory() {
    if (inventory.isEmpty()) {
      Print.printString("Your inventory is empty.\n", false);
    } else {
      for (Item i : inventory) {
        Print.printString("> \u001B[36m" + i.getName() + "\u001B[0m", false);
      }
      Print.printString("", false);
    }
  }
  
  /**
   * Processes a player entering a portal.
   * @param portal - chosen portal
   * @param house - main house
   * 
   */
  public void enter(Portal portal, House house) {
    currentRoom = house.getRoom(portal.getRoomName());
    currentWall = currentRoom.enter(portal.getWallName());
    currentWall.describe();
  }
  
  /**
   * Processes a player turning left.
   * 
   */
  public void turnLeft() {
    switch (currentWall.getDirection()) {
      case "n": {
        currentWall = currentRoom.getWestWall();
        break;
      }
      case "e": {
        currentWall = currentRoom.getNorthWall();
        break;
      }
      case "s": {
        currentWall = currentRoom.getEastWall();
        break;
      }
      case "w": {
        currentWall = currentRoom.getSouthWall();
        break;
      }
      default: {
        break;
      }
    }
    currentWall.describe();
  }
  
  /**
   * Processes a player turning right.
   * 
   */
  public void turnRight() {
    switch (currentWall.getDirection()) {
      case "n": {
        currentWall = currentRoom.getEastWall();
        break;
      }
      case "e": {
        currentWall = currentRoom.getSouthWall();
        break;
      }
      case "s": {
        currentWall = currentRoom.getWestWall();
        break;
      }
      case "w": {
        currentWall = currentRoom.getNorthWall();
        break;
      }
      default: {
        break;
      }
    }
    currentWall.describe();
  }
  
  /**
   * Processes a player turning around.
   * 
   */
  public void turnAround() {
    switch (currentWall.getDirection()) {
      case "n": {
        currentWall = currentRoom.getSouthWall();
        break;
      }
      case "e": {
        currentWall = currentRoom.getWestWall();
        break;
      }
      case "s": {
        currentWall = currentRoom.getNorthWall();
        break;
      }
      case "w": {
        currentWall = currentRoom.getEastWall();
        break;
      }
      default: {
        break;
      }
    }
    currentWall.describe();
  }
}
