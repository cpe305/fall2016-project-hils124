package abandoned.entities;
import abandoned.game.Main;
import abandoned.house.House;
import abandoned.house.Room;
import abandoned.house.Wall;

public class Item {
  
  private String name;
  private EntityUseType useType;
  private boolean isTakeable;

  public Item() {
  }

  public Item(String name, EntityUseType useType, boolean isTakeable) {
    this.name = name;
    this.useType = useType;
    this.isTakeable = isTakeable;
  }

  public String getName() {
    return this.name;
  }
  
  public EntityUseType getUseType() {
    return this.useType;
  }
  
  public boolean getIsTakeable() {
    return this.isTakeable;
  }
  
  public boolean use(Item item, House house, Room curRoom, Wall curWall) {
    return this.useType.action.use(item, Main.house, curRoom, curWall);  
  }
}
