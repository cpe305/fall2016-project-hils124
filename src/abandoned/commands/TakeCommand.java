package abandoned.commands;

/**
 * Class to model a take command.
 * @author hils124
 */
public class TakeCommand implements Command {
  private CommandProcessor cmdProcess;

  /**
   * Public constructor.
   */
  public TakeCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.take();
  }
}