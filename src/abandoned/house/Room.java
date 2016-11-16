package abandoned.house;

import abandoned.game.Main;

public class Room {

  private String name;
  private Wall westWall;
  private Wall eastWall;
  private Wall northWall;
  private Wall southWall;

  public Room() {
  }

  public Room(String name, Wall westWall, Wall eastWall, Wall northWall, Wall southWall) {
    this.name = name;
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
  
  public Wall enter(String wallName) throws Exception {
    Wall newWall;
    if ("westWall".equals(wallName)) {
      newWall = this.westWall;
    }
    else if ("eastWall".equals(wallName)) {
      newWall = this.eastWall;
    }
    else if ("northWall".equals(wallName)) {
      newWall = this.northWall;
    }
    else {
      newWall = this.southWall;
    }  
    Main.scrollText("\nYou have entered the " + this.name + ".\n");
    return newWall;
  }
}
