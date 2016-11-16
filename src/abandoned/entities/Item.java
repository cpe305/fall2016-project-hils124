package abandoned.entities;

import abandoned.game.Main;
import abandoned.game.Player;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;


public class Item {
  
  private String name;
  private String description;
  private EntityUseType useType;
  private boolean isTakeable;

  public Item() {
  }

  public Item(String name, String description, EntityUseType useType, boolean isTakeable) {
    this.name = name;
    this.description = description;
    this.useType = useType;
    this.isTakeable = isTakeable;
  }

  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public EntityUseType getUseType() {
    return this.useType;
  }
  
  public boolean getIsTakeable() {
    return this.isTakeable;
  }
  
  public boolean use(Player player, Item item, House house, Room curRoom, Wall curWall) throws Exception {
    if (this.useType != null) {
      return this.useType.getAction().use(player, item, Main.house, curRoom, curWall);  
    }
    return false;
  }
}
