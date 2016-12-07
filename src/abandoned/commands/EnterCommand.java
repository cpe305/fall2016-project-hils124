package abandoned.commands;

/**
 * Class to model an enter command.
 * @author hils124
 */
public class EnterCommand implements Command {
  private CommandProcessor cmdProcess;

  /**
   * Public constructor.
   */
  public EnterCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.enter();
  }
}
