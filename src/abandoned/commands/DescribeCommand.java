package abandoned.commands;

/**
 * Class to model a describe command.
 * @author hils124
 */
public class DescribeCommand implements Command {
  private CommandProcessor cmdProcess;

  /**
   * Public constructor.
   * @param cmdProcess - specifies cmdProcessor
   */
  public DescribeCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.describe();
  }
}