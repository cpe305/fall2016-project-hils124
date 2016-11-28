package abandoned.house;

import abandoned.game.Print;

/**
 * Class to model a room.
 * @author hils124
 */
public class Room {

  private String name;
  private String description;
  private boolean seen;
  private Wall westWall;
  private Wall eastWall;
  private Wall northWall;
  private Wall southWall;

  /**
   * Empty constructor for JSON serializing.
   */
  public Room() {
    //empty for JSON serializing
  }

  /**
   * Public constructor.
   * @param name - room name
   * @param descript - room description
   * @param westWall - room's west wall
   * @param eastWall - room's east wall
   * @param northWall - room's north wall
   * @param southWall - room's south wall
   */
  public Room(String name, String descript, Wall westWall, Wall eastWall, Wall northWall,
      Wall southWall) {
    this.name = name;
    this.description = descript;
    this.seen = false;
    this.westWall = westWall;
    this.eastWall = eastWall;
    this.northWall = northWall;
    this.southWall = southWall;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() { 
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String descript) {
    this.description = descript;
  }

  public void setWestWall(Wall wall) {
    this.westWall = wall;
  }

  public Wall getWestWall() {
    return this.westWall;
  }

  public void setEastWall(Wall wall) {
    this.eastWall = wall;
  }

  public Wall getEastWall() {
    return this.eastWall;
  }

  public void setNorthWall(Wall wall) {
    this.northWall = wall;
  }

  public Wall getNorthWall() {
    return this.northWall;
  }

  public void setSouthWall(Wall wall) {
    this.southWall = wall;
  }

  public Wall getSouthWall() {
    return this.southWall;
  }
  
  /**
   * Processes a portal being entered.
   * @param wallName - name of the new wall
   * @return new wall that the player is now facing
   * 
   */
  public Wall enter(String wallName) {
    Wall newWall;
    if ("westWall".equals(wallName)) {
      newWall = this.westWall;
    } else if ("eastWall".equals(wallName)) {
      newWall = this.eastWall;
    } else if ("northWall".equals(wallName)) {
      newWall = this.northWall;
    } else {
      newWall = this.southWall;
    }
    if (!seen) {
      Print.printString(this.description, true);
      seen = true;
    } else {
      Print.printString("\nYou have entered the " + this.name + ".\n", true);
    }
    return newWall;
  }
}
