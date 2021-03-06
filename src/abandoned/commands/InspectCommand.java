package abandoned.commands;

/**
 * Class to model an inspect command.
 * @author hils124
 */
public class InspectCommand implements Command {
  private CommandProcessor cmdProcess;

  /**
   * Public constructor.
   * @param cmdProcess - specifies cmdProcessor
   */
  public InspectCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.inspect();
  }
}