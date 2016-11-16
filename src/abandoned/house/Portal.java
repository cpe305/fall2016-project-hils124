package abandoned.house;

public class Portal {
  private String name;
  private String type;
  private String roomName;
  private String wallName;
  private String description;
  
  public Portal() {
    
  }
  
  public Portal(String name, String type, String roomName, String wallName, String description) {
    this.name = name;
    this.type = type;
    this.roomName = roomName;
    this.wallName = wallName;
    this.description = description;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getRoomName() {
    return this.roomName;
  }
  
  public String getWallName() {
    return this.wallName;
  }
  
  public String getDescription() {
    return this.description;
  }
}
