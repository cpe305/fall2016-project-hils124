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
      removeItem(item);
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
  
  /**
   * Removing item from player's inventory.
   * @param item - item to remove
   */
  public void removeItem(Item item) {
    inventory.remove(item);
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
    String direction = currentWall.getDirection();
    if ("n".equals(direction)) {
      currentWall = currentRoom.getWestWall();
    } else if ("e".equals(direction)) {
      currentWall = currentRoom.getNorthWall();
    } else if ("s".equals(direction)) {
      currentWall = currentRoom.getEastWall();
    } else {
      currentWall = currentRoom.getSouthWall();
    }
    currentWall.describe();
  }
  
  /**
   * Processes a player turning right.
   * 
   */
  public void turnRight() {
    String direction = currentWall.getDirection();
    if ("n".equals(direction)) {
      currentWall = currentRoom.getEastWall();
    } else if ("e".equals(direction)) {
      currentWall = currentRoom.getSouthWall();
    } else if ("s".equals(direction)) {
      currentWall = currentRoom.getWestWall();
    } else {
      currentWall = currentRoom.getNorthWall();
    }
    currentWall.describe();
  }
  
  /**
   * Processes a player turning around.
   * 
   */
  public void turnAround() {
    String direction = currentWall.getDirection();
    if ("n".equals(direction)) {
      currentWall = currentRoom.getSouthWall();
    } else if ("e".equals(direction)) {
      currentWall = currentRoom.getWestWall();
    } else if ("s".equals(direction)) {
      currentWall = currentRoom.getNorthWall();
    } else {
      currentWall = currentRoom.getEastWall();
    }
    currentWall.describe();
  }
}
