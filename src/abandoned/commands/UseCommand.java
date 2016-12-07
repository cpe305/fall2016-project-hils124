package abandoned.commands;

/**
 * Class to model a use command.
 * @author hils124
 */
public class UseCommand implements Command {
  private CommandProcessor cmdProcess;

  /**
   * Public constructor.
   */
  public UseCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.use();
  }
}