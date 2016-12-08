package abandoned.commands;

import abandoned.entities.Item;
import abandoned.game.Ansi;
import abandoned.game.GlobalHouse;
import abandoned.game.Print;
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
  private int artifactCount;
  private Room currentRoom;
  private Wall currentWall;

  /**
   * Empty constructor for JSON serializing.
   */
  
  /**
   * Public constructor.
   */
  public Player() {
    inventory = new ArrayList<>();
    this.artifactCount = 0;
    this.currentRoom = GlobalHouse.get().getRoom("bedroom");
    this.currentWall = this.currentRoom.getNorthWall();
  }

  /**
   * Using an item.
   * @param item - chosen item
   * @return true on success
   * 
   */
  public boolean useItem(Item item) {
    boolean success = item.use(currentRoom, currentWall);
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
  
  public void setArtifactCount(int count) {
    this.artifactCount = count;
  }
  
  public int getArtifactCount() {
    return this.artifactCount;
  }
  
  /**
   * Increments artifact count by 1.
   */
  public void incrementArtifactCount() {
    this.artifactCount = this.artifactCount + 1;
  }
  
  public void setCurrentWall(Wall wall) {
    this.currentWall = wall;
  }
  
  public Wall getCurrentWall() {
    return this.currentWall;
  }
  
  /**
   * Displays everything in the player's inventory.
   */
  public void displayInventory() {
    if (inventory.isEmpty()) {
      Print.printString("Your inventory is empty.\n", false);
    } else {
      for (Item i : inventory) {
        if ("artifacts".equals(i.getName())) {
          Print.printString("> " + Ansi.ITEM + i.getName() + Ansi.RESET + " ("
              + this.getArtifactCount() + ")", false);
        } else {
          Print.printString("> " + Ansi.ITEM + i.getName() + Ansi.RESET, false);
        }
      }
      Print.printString("", false);
    }
  }
  
  /**
   * Processes a player entering a portal.
   * @param portal - chosen portal
   * 
   */
  public void enter(Portal portal) {
    currentRoom = GlobalHouse.get().getRoom(portal.getRoomName());
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
  
  /**
   * Gets the specified item in the inventory
   * @param name - name of the requested item
   * @return requested item if it is in the inventory. Else returns null.
   */
  public Item getItem(String name) {
    for (Item i: this.inventory) {
      if (i.getName().equals(name)) {
        return i;
      }
    }
    return null;
  }
}
