package abandoned.entities;

/**
 * Enumeration to separate item actions.
 * @author hils124
 *
 */

public enum EntityUseType {

  HANDLE(new HandleAction()),
  KEY(new KeyAction()),
  SCISSORS(new ScissorsAction()),
  PIN(new PinAction()),
  FLASHLIGHT(new FlashlightAction());
  
  private EntityAction action;
  
  /**
   * Private constructor.
   * @param action - item action
   */
  private EntityUseType(EntityAction action) {
    this.action = action;
  }
  
  public EntityAction getAction() {
    return this.action;
  }
}
