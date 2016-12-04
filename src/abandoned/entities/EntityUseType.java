package abandoned.entities;

/**
 * Enumeration to separate item actions.
 * @author hils124
 *
 */

public enum EntityUseType {

  HANDLE(new HandleAction(), "KITCHEN - EAST"),
  BROOM(new BroomAction(), "GARDEN - NORTH"),
  BUCKET(new BucketAction(), "GARDEN - WEST"),
  LETTER(new LetterAction(), "LIBRARY - SOUTH"),
  MATCHES(new MatchAction(), "LIBRARY - EAST"),
  FLASHLIGHT(new FlashlightAction(), "LIBRARY - EAST"),
  KEY(new KeyAction(), ""),
  SCISSORS(new ScissorsAction(), "GARDEN - SOUTH"),
  PIN(new PinAction(), "BEDROOM - WEST"),
  KNIFE(new KnifeAction(), "BEDROOM - NORTH"),
  WATER(new WaterAction(), "LIBRARY - EAST");
  
  private EntityAction action;
  private String wallName;
  
  /**
   * Private constructor.
   * @param action - item action
   */
  private EntityUseType(EntityAction action, String wallName) {
    this.action = action;
    this.wallName = wallName;
  }
  
  public EntityAction getAction() {
    return this.action;
  }
  
  public String getWall() {
    return this.wallName;
  }
}
