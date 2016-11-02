package abandoned.game;
import java.util.ArrayList;

import abandoned.house.*;

public class Player {
  private ArrayList<Item> inventory;
  private Room currentRoom;
  private Wall currentWall;

  public Player(House house) {
    inventory = new ArrayList<Item>();
    this.currentRoom = house.getRoom("bedroom");
    this.currentWall = this.currentRoom.getNorthWall();
  }

  public void useItem(Item item) {
    item.use();
    inventory.remove(item);
  }

  public void addItem(Item item) {
    inventory.add(item);
  }

  public ArrayList<Item> getInventory() {
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
  
  public void turnLeft() {
    switch(currentWall.getDirection()) {
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
    }
    System.out.println(currentWall.getName());
  }
  
  public void turnRight() {
    switch(currentWall.getDirection()) {
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
    }
    System.out.println(currentWall.getName());
  }
  
  public void turnAround() {
    switch(currentWall.getDirection()) {
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
    }
    System.out.println(currentWall.getName());
  }
}
