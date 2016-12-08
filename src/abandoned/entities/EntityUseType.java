package abandoned.entities;

/**
 * Enumeration to separate item actions.
 * @author hils124
 *
 */

public enum EntityUseType {

  ARTIFACT(new ArtifactAction(), "BASEMENT - WEST"),
  BAR(new BarAction(), "BASEMENT - SOUTH"),
  BROOM(new BroomAction(), "GARDEN - NORTH"),
  BUCKET(new BucketAction(), "GARDEN - WEST"),
  FLASHLIGHT(new FlashlightAction(), "LIBRARY - EAST"),
  HAMMER(new HammerAction(), "ATTIC - EAST"),
  HANDLE(new HandleAction(), "KITCHEN - EAST"),
  KEY(new KeyAction(), "ATTIC - SOUTH"),
  KNIFE(new KnifeAction(), "BEDROOM - NORTH"),
  LETTER(new LetterAction(), "LIBRARY - SOUTH"),
  MATCHES(new MatchAction(), "LIBRARY - EAST"),
  MIRROR(new MirrorAction(), "GARDEN - EAST"),
  NOTE(new NoteAction(), "ATTIC - NORTH"),
  PIN(new PinAction(), "BEDROOM - WEST"),
  SCISSORS(new ScissorsAction(), "GARDEN - SOUTH"),
  SHOVEL(new ShovelAction(), "GARDEN - EAST"),
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
