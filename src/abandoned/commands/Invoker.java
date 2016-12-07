package abandoned.commands;

/**
 * Class to handle all commands.
 * @author hils124
 */
public class Invoker {
  Command cmd;
  
  /**
   * Method to execute a given command.
   */
  public void executeCommand(Command cmd) {
    this.cmd = cmd;
    cmd.execute();
  }
}