package abandoned.game;

/**
 * Class of ANSI constants for console colors.
 * @author hils124
 *
 */
public class Ansi {
 
  /**
   * item color.
   */
  public static final String ITEM = "\u001B[36m";
  
  /**
   * container color.
   */
  public static final String BIN = "\u001B[32m";
  
  /**
   * portal color.
   */
  public static final String PORTAL = "\u001B[33m";
  
  /**
   * reset color.
   */
  public static final String RESET = "\u001B[0m";
  
  /**
   * clear terminal window.
   */
  public static final String CLEAR = "\033[H\033[2J";
  
  private Ansi() {
    throw new IllegalAccessError("Ansi class");
  }
  
        
}
