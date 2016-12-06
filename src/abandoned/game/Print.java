package abandoned.game;

/**
 * Class for printing strings.
 * @author hils124
 */
public class Print {
  
  public static boolean printToConsole = true;
  
  private Print() {
    throw new IllegalAccessError("Print class");
  }
  
  /**
   * Prints a string.
   * @param str - message to print
   * @param scroll - true if it is scrolling text
   * 
   */
  public static void printString(String str, boolean scroll) {
    if (printToConsole) {
      if (scroll) {
        for (int i = 0; i < str.length(); i++) {
          try {
            System.out.print(str.charAt(i));
            Thread.sleep(50);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        }
      } else {
        System.out.println(str);
      }
    }
  }
}
