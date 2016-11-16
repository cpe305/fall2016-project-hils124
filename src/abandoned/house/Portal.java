package abandoned.house;

/**
 * Class to model a portal.
 * @author hils124
 */
public class Portal {
  private String name;
  private String type;
  private String roomName;
  private String wallName;
  private String description;
  
  /**
   * Empty constructor for JSON serializing.
   */
  public Portal() {
    
  }
  
  /**
   * Public constructor.
   * @param name - portal name
   * @param type - portal type
   * @param roomName - room that the portal leads to
   * @param wallName - wall that the portal leads to 
   * @param description - portal description
   */
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
