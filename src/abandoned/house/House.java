package abandoned.house;
import java.util.ArrayList;

public class House {
  private ArrayList<Room> rooms;

  public House() {
  }

  public House(ArrayList<Room> rooms) {
    this.rooms = rooms;
  }

  public ArrayList<Room> getRooms() {
    return this.rooms;
  }
  
  public Room getRoom(String name) {
    for (Room r: this.rooms) {
      if (r.getName().equals(name)) {
        return r;
      }
    }
    return null;
  }

  public void printHouse() {
    for (Room room : rooms) {
      System.out.println("Room: " + room.getName());
      Wall leftWall = room.getLeftWall();
      Wall rightWall = room.getRightWall();
      Wall frontWall = room.getFrontWall();
      Wall backWall = room.getBackWall();
      System.out.println("  Left:");
      for (Container c : leftWall.getContainers()) {
        System.out.println("    " + c.getName());
        for (Item i : c.getItems()) {
          System.out.println("      -" + i.getName());
        }
      }
      for (Item i : leftWall.getItems()) {
        System.out.println("   " + i.getName());
      }

      System.out.println("  Right:");
      for (Container c : rightWall.getContainers()) {
        System.out.println("    " + c.getName());
        for (Item i : c.getItems()) {
          System.out.println("      -" + i.getName());
        }
      }
      for (Item i : rightWall.getItems()) {
        System.out.println("    " + i.getName());
      }

      System.out.println("  Front:");
      for (Container c : frontWall.getContainers()) {
        System.out.println("    " + c.getName());
        for (Item i : c.getItems()) {
          System.out.println("      -" + i.getName());
        }
      }
      for (Item i : frontWall.getItems()) {
        System.out.println("    " + i.getName());
      }

      System.out.println("  Back:");
      for (Container c : backWall.getContainers()) {
        System.out.println("    " + c.getName());
        for (Item i : c.getItems()) {
          System.out.println("      -" + i.getName());
        }
      }
      for (Item i : backWall.getItems()) {
        System.out.println("    " + i.getName());
      }
      System.out.println();
    }
  }
}
