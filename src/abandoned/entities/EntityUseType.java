package abandoned.entities;

/**
 * Enumeration to separate item actions.
 * @author hils124
 *
 */

public enum EntityUseType {
  /**
   * Models a match action.
   */
  MATCH(new MatchAction());
  
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
