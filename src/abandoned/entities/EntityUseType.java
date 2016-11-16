package abandoned.entities;

public enum EntityUseType {
//  LOCK, INTERACTION, OPEN, ENTRANCE;
  MATCH(new MatchAction());
  
  private EntityAction action;
  
  private EntityUseType(EntityAction action) {
    this.action = action;
  }
  
  public EntityAction getAction() {
    return this.action;
  }
}
