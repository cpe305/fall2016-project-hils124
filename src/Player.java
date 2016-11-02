import java.util.ArrayList;

public class Player {
  private ArrayList<Item> inventory;
  private Room currentRoom;
  private Wall currentWall;

  public Player(House house) {
    inventory = new ArrayList<Item>();
    this.currentRoom = house.getRoom("bedroom");
    this.currentWall = this.currentRoom.getFrontWall();
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
}
