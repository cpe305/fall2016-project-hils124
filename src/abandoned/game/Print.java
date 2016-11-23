package abandoned.game;

/**
 * Class for printing strings.
 * @author hils124
 */
public class Print {
  
  private Print() {
    throw new IllegalAccessError("Print class");
  }
  
  /**
   * Prints a string.
   * @param str - message to print
   * @param scroll - true if it is scrolling text
   * @throws InterruptedException
   * 
   */
  public static void printString(String str, boolean scroll) throws InterruptedException {
    if (scroll) {
      for (int i = 0; i < str.length(); i++) {
        System.out.print(str.charAt(i));
        Thread.sleep(50);
      }
    } else {
      System.out.println(str);
    }
  }
}
