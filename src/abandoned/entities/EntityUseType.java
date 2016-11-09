package abandoned.entities;

public enum EntityUseType {
//  LOCK, INTERACTION, OPEN, ENTRANCE;
  MATCH(new MatchAction());
  
  public EntityAction action;
  
  private EntityUseType(EntityAction action) {
    this.action = action;
  }
}
